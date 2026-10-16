const FOLDER_URL =
  import.meta.env.VITE_FOLDER_SERVICE_URL || "http://localhost:8080";
const FILE_URL =
  import.meta.env.VITE_FILE_SERVICE_URL || "http://localhost:8081";

async function request(url, options = {}) {
  const response = await fetch(url, options);
  if (!response.ok) {
    let message = `${response.status} ${response.statusText}`;
    try {
      message = (await response.json()).message || message;
    } catch {}
    throw new Error(message);
  }
  const type = response.headers.get("content-type") || "";
  return type.includes("application/json") ? response.json() : response.text();
}

export const api = {
  folderBase: `${FOLDER_URL}/api/folder`,
  fileBase: `${FILE_URL}/api/file`,
  listFolders: () => request(`${FOLDER_URL}/api/folder`),
  listFiles: () => request(`${FILE_URL}/api/file`),
  createFolder: (name, parentId) =>
    request(`${FOLDER_URL}/api/folder/create`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, parentId: parentId ?? null }),
    }),
  upload: (file, folderId) => {
    const body = new FormData();
    body.append("name", file.name);
    if (folderId != null) body.append("folderId", folderId);
    body.append("file", file);
    return request(`${FILE_URL}/api/file/upload`, { method: "POST", body });
  },
  deleteFolder: (id) =>
    request(`${FOLDER_URL}/api/folder/${id}`, { method: "DELETE" }),
  deleteFile: (id) =>
    request(`${FILE_URL}/api/file/${id}`, { method: "DELETE" }),
  downloadUrl: (id) => `${FILE_URL}/api/file/download/${id}`,
  health: (url) => fetch(`${url}/actuator/health`).then((r) => r.ok),
};
