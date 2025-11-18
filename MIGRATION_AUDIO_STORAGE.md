# Audio File Storage Migration

## Changes Made

This migration changes the audio file storage from database BYTEA to file system storage.

### Database Changes

**Old Schema:**
```sql
CREATE TABLE public.Sound (
    ...
    donnees_audio BYTEA
);
```

**New Schema:**
```sql
CREATE TABLE public.Sound (
    ...
    chemin character varying(500)
);
```

### Backend Changes

1. **Sound Entity (`Sound.java`)**
   - Replaced `byte[] donneesAudio` with `String chemin`
   - Updated getters and setters accordingly

2. **SoundController (`SoundController.java`)**
   - Upload endpoint now saves files to `sons/{type}/` directory
   - Files are named with pattern: `{sanitizedName}_{timestamp}.{extension}`
   - File path is stored in database instead of binary data
   - Download endpoint now reads from file system using `Resource`
   - Delete endpoint now removes both database record and physical file

### File Storage Structure

```
back/PEPs_back/sons/
├── README.md
├── Ambiance/
│   └── chant_mali_1234567890.mp3
├── Vocal/
│   └── cri_perroquet_1234567891.mp3
└── Naturel/
    └── eau_1234567892.wav
```

### Migration Steps

1. **Run the migration script:**
   ```sql
   -- Execute: sql/migration_donnees_audio_to_chemin.sql
   ```

2. **Or drop and recreate the table:**
   ```sql
   -- Execute: sql/requete creation tables.sql
   ```

3. **Create the sons directory** (already created at `back/PEPs_back/sons/`)

4. **Re-upload all audio files** through the Angular application

### Benefits

- **Performance**: No large BYTEA fields slowing down queries
- **Scalability**: File system handles large files better than database
- **Backup**: Easier to backup files separately from database
- **Migration**: Easier to move files between servers
- **Storage**: More efficient storage and retrieval

### API Behavior

**Upload (POST /sounds):**
- Receives multipart form data with name, type, and file
- Creates directory structure if needed: `sons/{type}/`
- Saves file with unique name
- Stores file path in database

**Download (GET /sounds/{id}/file):**
- Retrieves file path from database
- Reads file from disk
- Returns file as Resource with appropriate content type

**Delete (DELETE /sounds/{id}):**
- Deletes physical file from disk
- Removes database record

### Angular Frontend

No changes needed in the Angular frontend. The API interface remains the same:
- Upload: POST multipart/form-data to `/sounds`
- Download: GET `/sounds/{id}/file`
- List: GET `/sounds`
- Update: PUT `/sounds/{id}`
- Delete: DELETE `/sounds/{id}`

### Notes

- Audio files in `sons/` directory are excluded from version control (see `.gitignore`)
- Only `sons/README.md` is tracked in git
- File names are sanitized and timestamped to avoid conflicts
- Supported formats: mp3, wav, ogg, m4a
