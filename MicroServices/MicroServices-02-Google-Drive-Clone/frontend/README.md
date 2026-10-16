# Orbit Drive frontend

This is the React/Tailwind frontend for the FolderService and FileService in this
project. It uses the existing REST APIs directly, so it is also a small manual
test console for creating folders, uploading files, downloading files, and
deleting records.

## Run locally

1. Start both Spring Boot services.
2. Copy `.env.example` to `.env` if your service ports differ from the defaults.
3. Run:

```bash
npm install
npm run dev
```

The app is served at `http://localhost:5173`. The default API URLs are
`http://localhost:8080` for FolderService and `http://localhost:8081` for
FileService.
