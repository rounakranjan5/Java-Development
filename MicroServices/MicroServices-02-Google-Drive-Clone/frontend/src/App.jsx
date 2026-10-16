import { useEffect, useMemo, useRef, useState } from "react";
import {
  ChevronRight,
  Cloud,
  Download,
  File,
  FileCode2,
  FileImage,
  FileText,
  Folder,
  FolderPlus,
  HardDrive,
  LayoutGrid,
  List,
  Menu,
  Plus,
  RefreshCw,
  Search,
  Settings2,
  ShieldCheck,
  Trash2,
  Upload,
  X,
  Zap,
} from "lucide-react";
import { api } from "./lib/api";

const cn = (...classes) => classes.filter(Boolean).join(" ");
const formatSize = (size) => {
  if (!size) return "—";
  if (size < 1024) return `${size} B`;
  if (size < 1024 ** 2) return `${(size / 1024).toFixed(1)} KB`;
  return `${(size / 1024 ** 2).toFixed(1)} MB`;
};
const fileIcon = (name) => {
  const ext = name?.split(".").pop()?.toLowerCase();
  if (["png", "jpg", "jpeg", "gif", "svg"].includes(ext))
    return <FileImage className="text-fuchsia-500" size={21} />;
  if (["js", "jsx", "java", "ts", "py"].includes(ext))
    return <FileCode2 className="text-amber-500" size={21} />;
  if (["pdf", "doc", "txt"].includes(ext))
    return <FileText className="text-sky-500" size={21} />;
  return <File className="text-slate-400" size={21} />;
};

function App() {
  const [folders, setFolders] = useState([]);
  const [files, setFiles] = useState([]);
  const [currentFolder, setCurrentFolder] = useState(null);
  const [crumbs, setCrumbs] = useState([{ id: null, name: "My Drive" }]);
  const [query, setQuery] = useState("");
  const [view, setView] = useState("grid");
  const [modal, setModal] = useState(null);
  const [folderName, setFolderName] = useState("");
  const [loading, setLoading] = useState(true);
  const [notice, setNotice] = useState(null);
  const [health, setHealth] = useState({ folder: null, file: null });
  const [mobileMenu, setMobileMenu] = useState(false);
  const [busy, setBusy] = useState(false);
  const uploadRef = useRef(null);

  const load = async () => {
    setLoading(true);
    try {
      const [nextFolders, nextFiles] = await Promise.all([
        api.listFolders(),
        api.listFiles(),
      ]);
      setFolders(nextFolders || []);
      setFiles(nextFiles || []);
    } catch (error) {
      setNotice({
        type: "error",
        text: `Could not load your drive: ${error.message}`,
      });
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    load();
    api
      .health(api.folderBase.replace("/api/folder", ""))
      .then((ok) => setHealth((h) => ({ ...h, folder: ok })))
      .catch(() => setHealth((h) => ({ ...h, folder: false })));
    api
      .health(api.fileBase.replace("/api/file", ""))
      .then((ok) => setHealth((h) => ({ ...h, file: ok })))
      .catch(() => setHealth((h) => ({ ...h, file: false })));
  }, []);
  useEffect(() => {
    if (!notice) return;
    const timer = setTimeout(() => setNotice(null), 4200);
    return () => clearTimeout(timer);
  }, [notice]);

  const visibleFolders = useMemo(
    () =>
      folders.filter(
        (f) =>
          (f.parentId ?? null) === currentFolder &&
          f.name?.toLowerCase().includes(query.toLowerCase()),
      ),
    [folders, currentFolder, query],
  );
  const visibleFiles = useMemo(
    () =>
      files.filter(
        (f) =>
          (f.folderId ?? null) === currentFolder &&
          f.name?.toLowerCase().includes(query.toLowerCase()),
      ),
    [files, currentFolder, query],
  );
  const usedBytes = files.reduce((sum, f) => sum + (f.size || 0), 0);

  const openFolder = (folder) => {
    setCurrentFolder(folder.id);
    setCrumbs((items) => {
      const existingIndex = items.findIndex((item) => item.id === folder.id);
      return existingIndex >= 0
        ? items.slice(0, existingIndex + 1)
        : [...items, { id: folder.id, name: folder.name }];
    });
    setQuery("");
    setMobileMenu(false);
  };
  const goCrumb = (index) => {
    const crumb = crumbs[index];
    setCurrentFolder(crumb.id);
    setCrumbs(crumbs.slice(0, index + 1));
    setQuery("");
    setMobileMenu(false);
  };
  const createFolder = async (event) => {
    event.preventDefault();
    if (!folderName.trim()) return;
    try {
      await api.createFolder(folderName.trim(), currentFolder);
      setFolderName("");
      setModal(null);
      setNotice({ type: "success", text: "Folder created" });
      await load();
    } catch (error) {
      setNotice({ type: "error", text: error.message });
    }
  };
  const upload = async (event) => {
    const selected = [...(event.target.files || [])];
    if (!selected.length) return;
    setBusy(true);
    try {
      await Promise.all(
        selected.map((file) => api.upload(file, currentFolder)),
      );
      setNotice({
        type: "success",
        text: `${selected.length} file${selected.length > 1 ? "s" : ""} uploaded`,
      });
      await load();
    } catch (error) {
      setNotice({ type: "error", text: `Upload failed: ${error.message}` });
    } finally {
      setBusy(false);
    }
    event.target.value = "";
  };
  const remove = async (kind, id) => {
    if (!window.confirm(`Delete this ${kind}?`)) return;
    try {
      await (kind === "folder" ? api.deleteFolder(id) : api.deleteFile(id));
      setNotice({
        type: "success",
        text: `${kind[0].toUpperCase() + kind.slice(1)} deleted`,
      });
      await load();
    } catch (error) {
      setNotice({ type: "error", text: error.message });
    }
  };

  const refresh = async () => {
    setBusy(true);
    await load();
    setBusy(false);
    setNotice({ type: "success", text: "Drive refreshed" });
  };

  return (
    <div className="grain min-h-screen bg-paper">
      <aside className="fixed inset-y-0 left-0 hidden w-[246px] border-r border-line bg-[#f2f2ef] px-5 py-6 lg:flex lg:flex-col">
        <div className="flex items-center gap-3 px-2">
          <div className="grid h-9 w-9 place-items-center rounded-xl bg-ink text-lime">
            <Cloud size={20} fill="currentColor" />
          </div>
          <span className="font-display text-xl font-bold tracking-tight">
            orbit<span className="text-slate-400">.</span>
          </span>
        </div>
        <button
          onClick={() => setModal("folder")}
          className="mt-10 flex items-center justify-center gap-2 rounded-xl bg-ink px-4 py-3 text-sm font-semibold text-white shadow-sm hover:bg-slate-700"
        >
          <Plus size={17} /> New item
        </button>
        <nav className="mt-8 space-y-1 text-sm font-medium">
          <SideItem
            active={currentFolder === null}
            icon={<HardDrive size={18} />}
            label="My Drive"
            onClick={() => goCrumb(0)}
          />
        </nav>
        <div className="mt-6 rounded-2xl border border-line bg-white/70 p-4">
          <div className="flex items-center gap-2 text-xs font-semibold uppercase tracking-wider text-slate-400">
            <ShieldCheck size={14} className="text-emerald-500" /> Workspace
          </div>
          <p className="mt-2 text-sm font-semibold text-ink">Your files are synced</p>
          <p className="mt-1 text-xs leading-5 text-slate-400">Folders and files are managed by your local services.</p>
        </div>
        <div className="mt-auto rounded-2xl border border-line bg-white p-4">
          <div className="mb-3 flex items-center justify-between">
            <span className="text-xs font-semibold text-slate-500">
              STORAGE
            </span>
            <Zap size={16} className="text-lime-600" />
          </div>
          <div className="mb-2 h-1.5 overflow-hidden rounded-full bg-slate-100">
            <div className="h-full w-[18%] rounded-full bg-ink" />
          </div>
          <p className="text-xs text-slate-500">
            {formatSize(usedBytes)} of 15 GB used
          </p>
        </div>
        <button className="mt-5 flex items-center gap-3 px-2 text-sm font-medium text-slate-500 hover:text-ink">
          <Settings2 size={18} /> Service settings
        </button>
      </aside>

      <main className="lg:ml-[246px]">
        <header className="sticky top-0 z-20 flex h-[76px] items-center justify-between border-b border-line bg-paper/90 px-5 backdrop-blur-md md:px-10">
          <div className="flex items-center gap-3 lg:hidden">
            <button onClick={() => setMobileMenu((open) => !open)} className="rounded-lg p-1 text-slate-500 hover:bg-white hover:text-ink" aria-label="Open navigation"><Menu size={19} /></button>
            <div className="grid h-8 w-8 place-items-center rounded-lg bg-ink text-lime">
              <Cloud size={17} fill="currentColor" />
            </div>
            <span className="font-display font-bold">orbit.</span>
          </div>
          <div className="relative hidden max-w-md flex-1 md:block">
            <Search
              className="absolute left-3 top-2.5 text-slate-400"
              size={18}
            />
            <input
              value={query}
              onChange={(e) => setQuery(e.target.value)}
              placeholder="Search this folder"
              className="w-full rounded-xl border border-line bg-white py-2.5 pl-10 pr-4 text-sm outline-none transition focus:border-slate-400"
            />
          </div>
          <div className="flex items-center gap-3">
            <button onClick={refresh} className="hidden items-center gap-2 rounded-xl border border-line bg-white px-3 py-2 text-xs font-semibold text-slate-500 hover:border-slate-400 hover:text-ink sm:flex" title="Refresh drive">
              <RefreshCw size={14} className={busy ? "animate-spin" : ""} /> Refresh
            </button>
            <div className="hidden items-center gap-2 rounded-full border border-line bg-white px-3 py-1.5 text-xs sm:flex">
              <span
                className={cn(
                  "h-2 w-2 rounded-full",
                  health.folder === false || health.file === false
                    ? "bg-red-400"
                    : health.folder && health.file
                      ? "bg-emerald-400"
                      : "bg-amber-400",
                )}
              />
              {health.folder && health.file
                ? "Services online"
                : health.folder === false || health.file === false
                  ? "Service offline"
                  : "Checking services"}
            </div>
            <div className="grid h-9 w-9 place-items-center rounded-full bg-[#d8f36a] text-sm font-bold">
              AK
            </div>
          </div>
        </header>
        {mobileMenu && <div className="border-b border-line bg-[#f2f2ef] px-5 py-4 lg:hidden"><button onClick={() => goCrumb(0)} className={cn("flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-left text-sm font-semibold", currentFolder === null ? "bg-white" : "text-slate-500")}><HardDrive size={18} /> My Drive</button><button onClick={() => setModal("folder")} className="mt-2 flex w-full items-center gap-3 rounded-xl bg-ink px-3 py-2.5 text-left text-sm font-semibold text-white"><FolderPlus size={18} /> New folder</button></div>}
        <div className="border-b border-line bg-paper px-5 py-3 md:hidden">
          <div className="relative">
            <Search className="absolute left-3 top-2.5 text-slate-400" size={17} />
            <input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search this folder" className="w-full rounded-xl border border-line bg-white py-2.5 pl-10 pr-4 text-sm outline-none focus:border-slate-400" />
          </div>
        </div>
        <div className="mx-auto max-w-[1380px] px-5 py-8 md:px-10 md:py-10">
          <div className="mb-8 flex flex-col justify-between gap-5 md:flex-row md:items-end">
            <div>
              <p className="mb-2 text-xs font-semibold uppercase tracking-[.18em] text-slate-400">
                My Drive / {currentFolder ? "Folder" : "Root"}
              </p>
              <h1 className="font-display text-3xl font-bold tracking-tight md:text-4xl">
                {crumbs.at(-1).name}
              </h1>
              <div className="mt-3 flex flex-wrap items-center gap-1 text-sm text-slate-400">
                {crumbs.map((crumb, i) => (
                  <span
                    key={`${crumb.id}-${i}`}
                    className="flex items-center gap-1"
                  >
                    {i > 0 && <ChevronRight size={14} />}
                    {i === crumbs.length - 1 ? (
                      <span className="font-medium text-ink">{crumb.name}</span>
                    ) : (
                      <button
                        onClick={() => goCrumb(i)}
                        className="hover:text-ink"
                      >
                        {crumb.name}
                      </button>
                    )}
                  </span>
                ))}
              </div>
            </div>
            <div className="flex items-center gap-2">
              <input
                ref={uploadRef}
                type="file"
                multiple
                className="hidden"
                onChange={upload}
              />
              <button
                disabled={busy}
                onClick={() => uploadRef.current?.click()}
                className="flex items-center gap-2 rounded-xl border border-line bg-white px-4 py-2.5 text-sm font-semibold hover:border-slate-400"
              >
                {busy ? <RefreshCw size={16} className="animate-spin" /> : <Upload size={16} />} {busy ? "Working…" : "Upload"}
              </button>
              <button
                onClick={() => setModal("folder")}
                className="flex items-center gap-2 rounded-xl bg-ink px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-700"
              >
                <FolderPlus size={16} /> New folder
              </button>
            </div>
          </div>
          <div className="mb-9 grid grid-cols-2 gap-3 md:grid-cols-4">
            <Stat
              icon={<Folder size={18} />}
              label="Folders"
              value={folders.length}
              tint="bg-amber-100 text-amber-700"
            />
            <Stat
              icon={<File size={18} />}
              label="Files"
              value={files.length}
              tint="bg-sky-100 text-sky-700"
            />
            <Stat
              icon={<ShieldCheck size={18} />}
              label="Protected"
              value="100%"
              tint="bg-emerald-100 text-emerald-700"
            />
            <Stat
              icon={<Cloud size={18} />}
              label="Available"
              value="15 GB"
              tint="bg-fuchsia-100 text-fuchsia-700"
            />
          </div>
          <section>
            <div className="mb-4 flex items-center justify-between">
              <div>
                <h2 className="font-display text-lg font-bold">
                  Files & folders
                </h2>
                <p className="mt-1 text-sm text-slate-400">
                  {visibleFolders.length + visibleFiles.length} {query ? "matching " : ""}items here
                </p>
              </div>
              <div className="flex items-center rounded-lg border border-line bg-white p-1">
                <button
                  onClick={() => setView("grid")}
                  className={cn(
                    "rounded-md p-1.5",
                    view === "grid" ? "bg-ink text-white" : "text-slate-400",
                  )}
                >
                  <LayoutGrid size={16} />
                </button>
                <button
                  onClick={() => setView("list")}
                  className={cn(
                    "rounded-md p-1.5",
                    view === "list" ? "bg-ink text-white" : "text-slate-400",
                  )}
                >
                  <List size={16} />
                </button>
              </div>
            </div>
            {query && <div className="mb-4 flex items-center justify-between rounded-xl border border-sky-100 bg-sky-50 px-3 py-2 text-xs text-sky-700"><span>Filtering this folder for <strong>“{query}”</strong></span><button onClick={() => setQuery("")} className="font-semibold hover:underline">Clear search</button></div>}
            {loading ? (
              <div className="grid grid-cols-2 gap-4 md:grid-cols-4">
                <Skeleton />
                <Skeleton />
                <Skeleton />
                <Skeleton />
              </div>
            ) : visibleFolders.length + visibleFiles.length === 0 ? (
              <Empty query={query} onCreate={() => setModal("folder")} />
            ) : (
              <div
                className={cn(
                  view === "grid"
                    ? "grid grid-cols-2 gap-4 md:grid-cols-3 xl:grid-cols-4"
                    : "divide-y divide-line overflow-hidden rounded-2xl border border-line bg-white",
                )}
              >
                {visibleFolders.map((folder) => (
                  <FolderCard
                    key={`folder-${folder.id}`}
                    folder={folder}
                    view={view}
                    onOpen={() => openFolder(folder)}
                    onDelete={() => remove("folder", folder.id)}
                  />
                ))}
                {visibleFiles.map((file) => (
                  <FileCard
                    key={`file-${file.id}`}
                    file={file}
                    view={view}
                    onDelete={() => remove("file", file.id)}
                  />
                ))}
              </div>
            )}
          </section>
        </div>
      </main>
      {notice && (
        <div
          className={cn(
            "fixed bottom-6 right-6 z-30 flex max-w-sm items-center gap-3 rounded-xl border px-4 py-3 text-sm font-medium shadow-lg",
            notice.type === "error"
              ? "border-red-200 bg-red-50 text-red-700"
              : "border-emerald-200 bg-emerald-50 text-emerald-700",
          )}
        >
          <span>{notice.text}</span>
          <button onClick={() => setNotice(null)}>
            <X size={15} />
          </button>
        </div>
      )}
      {modal === "folder" && (
        <div className="fixed inset-0 z-40 grid place-items-center bg-ink/30 p-5 backdrop-blur-sm">
          <form
            onSubmit={createFolder}
            className="w-full max-w-md rounded-2xl bg-white p-6 shadow-2xl"
          >
            <div className="mb-5 flex items-start justify-between">
              <div>
                <h2 className="font-display text-xl font-bold">
                  Create a folder
                </h2>
                <p className="mt-1 text-sm text-slate-400">
                  Add a new space to your drive.
                </p>
              </div>
              <button
                type="button"
                onClick={() => setModal(null)}
                className="text-slate-400 hover:text-ink"
              >
                <X size={20} />
              </button>
            </div>
            <label className="text-xs font-semibold uppercase tracking-wider text-slate-500">
              Folder name
            </label>
            <input
              autoFocus
              value={folderName}
              onChange={(e) => setFolderName(e.target.value)}
              placeholder="e.g. Product assets"
              className="mt-2 w-full rounded-xl border border-line px-4 py-3 text-sm outline-none focus:border-ink"
            />
            <div className="mt-6 flex justify-end gap-2">
              <button
                type="button"
                onClick={() => setModal(null)}
                className="rounded-xl px-4 py-2.5 text-sm font-semibold text-slate-500 hover:bg-slate-100"
              >
                Cancel
              </button>
              <button className="rounded-xl bg-ink px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-700">
                Create folder
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
}

function SideItem({ icon, label, active, onClick }) {
  return (
    <button
      onClick={onClick}
      className={cn(
        "flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-left",
        active
          ? "bg-white text-ink shadow-sm"
          : "text-slate-500 hover:bg-white/70 hover:text-ink",
      )}
    >
      {icon}
      <span>{label}</span>
    </button>
  );
}
function Stat({ icon, label, value, tint }) {
  return (
    <div className="rounded-2xl border border-line bg-white p-4 md:p-5">
      <div
        className={cn("mb-4 grid h-9 w-9 place-items-center rounded-xl", tint)}
      >
        {icon}
      </div>
      <p className="text-xs font-medium text-slate-400">{label}</p>
      <p className="mt-1 font-display text-xl font-bold">{value}</p>
    </div>
  );
}
function FolderCard({ folder, view, onOpen, onDelete }) {
  return view === "list" ? (
    <div className="flex items-center gap-4 px-5 py-3 hover:bg-slate-50">
      <Folder className="fill-amber-100 text-amber-500" size={21} />
      <button
        onClick={onOpen}
        className="flex-1 text-left text-sm font-semibold hover:underline"
      >
        {folder.name}
      </button>
      <span className="text-xs text-slate-400">Folder</span>
      <button
        onClick={onDelete}
        className="p-2 text-slate-300 hover:text-red-500"
      >
        <Trash2 size={16} />
      </button>
    </div>
  ) : (
    <div className="group rounded-2xl border border-line bg-white p-4 transition hover:-translate-y-0.5 hover:border-slate-300 hover:shadow-md">
      <div className="mb-8 flex items-start justify-between">
        <button
          onClick={onOpen}
          className="grid h-11 w-11 place-items-center rounded-xl bg-amber-50"
        >
          <Folder className="fill-amber-100 text-amber-500" size={23} />
        </button>
        <button
          onClick={onDelete}
          className="text-slate-300 opacity-0 transition group-hover:opacity-100 hover:text-red-500"
        >
          <Trash2 size={16} />
        </button>
      </div>
      <button
        onClick={onOpen}
        className="max-w-full truncate text-left text-sm font-semibold hover:underline"
      >
        {folder.name}
      </button>
      <p className="mt-1 text-xs text-slate-400">Folder</p>
    </div>
  );
}
function FileCard({ file, view, onDelete }) {
  return view === "list" ? (
    <div className="flex items-center gap-4 px-5 py-3 hover:bg-slate-50">
      <div className="grid h-8 w-8 place-items-center rounded-lg bg-slate-50">
        {fileIcon(file.name)}
      </div>
      <a
        href={api.downloadUrl(file.id)}
        className="flex-1 truncate text-left text-sm font-semibold hover:underline"
      >
        {file.name}
      </a>
      <span className="hidden text-xs text-slate-400 sm:block">
        {formatSize(file.size)}
      </span>
      <a
        href={api.downloadUrl(file.id)}
        download={file.name}
        className="rounded-lg p-2 text-slate-300 hover:bg-sky-50 hover:text-sky-600"
        title={`Download ${file.name}`}
        aria-label={`Download ${file.name}`}
      >
        <Download size={16} />
      </a>
      <button
        onClick={onDelete}
        className="p-2 text-slate-300 hover:text-red-500"
      >
        <Trash2 size={16} />
      </button>
    </div>
  ) : (
    <div className="group rounded-2xl border border-line bg-white p-4 transition hover:-translate-y-0.5 hover:border-slate-300 hover:shadow-md">
      <div className="mb-8 flex items-start justify-between">
        <a
          href={api.downloadUrl(file.id)}
          download={file.name}
          className="grid h-11 w-11 place-items-center rounded-xl bg-slate-50"
          title={`Download ${file.name}`}
        >
          {fileIcon(file.name)}
        </a>
        <div className="flex items-center gap-1 opacity-0 transition group-hover:opacity-100">
          <a href={api.downloadUrl(file.id)} download={file.name} className="rounded-lg p-1.5 text-slate-300 hover:bg-sky-50 hover:text-sky-600" title={`Download ${file.name}`} aria-label={`Download ${file.name}`}><Download size={16} /></a>
          <button onClick={onDelete} className="rounded-lg p-1.5 text-slate-300 hover:bg-red-50 hover:text-red-500" title={`Delete ${file.name}`} aria-label={`Delete ${file.name}`}><Trash2 size={16} /></button>
        </div>
      </div>
      <a
        href={api.downloadUrl(file.id)}
        className="block truncate text-sm font-semibold hover:underline"
      >
        {file.name}
      </a>
      <p className="mt-1 text-xs text-slate-400">{formatSize(file.size)}</p>
    </div>
  );
}
function Skeleton() {
  return (
    <div className="h-36 animate-pulse rounded-2xl border border-line bg-white" />
  );
}
function Empty({ query, onCreate }) {
  return (
    <div className="rounded-2xl border border-dashed border-slate-300 bg-white py-16 text-center">
      <div className="mx-auto mb-4 grid h-12 w-12 place-items-center rounded-2xl bg-slate-100 text-slate-400">
        <Search size={22} />
      </div>
      <h3 className="font-display font-bold">
        {query ? "Nothing found" : "This space is empty"}
      </h3>
      <p className="mt-1 text-sm text-slate-400">
        {query
          ? "Try a different search term."
          : "Create a folder or upload a file to get started."}
      </p>
      {!query && (
        <button
          onClick={onCreate}
          className="mt-5 rounded-xl bg-ink px-4 py-2 text-sm font-semibold text-white"
        >
          Create folder
        </button>
      )}
    </div>
  );
}

export default App;
