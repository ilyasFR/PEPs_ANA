# Quick Start - Audio File Storage Migration

## What Changed?

Audio files are now stored on the file system instead of in the database.

## Setup Steps

### 1. Update Database Schema

Run one of these SQL scripts:

**Option A - Migration (if you have existing data):**
```bash
psql -U your_user -d your_database -f sql/migration_donnees_audio_to_chemin.sql
```

**Option B - Fresh Install:**
```bash
psql -U your_user -d your_database -f sql/requete creation tables.sql
```

### 2. Deploy Backend

The backend changes are already in place:
- `Sound.java` - Uses `chemin` field instead of `donneesAudio`
- `SoundController.java` - Saves files to disk in `sons/{type}/` directory
- `sons/` directory created at `back/PEPs_back/sons/`

### 3. Re-upload Audio Files

If you had existing audio files in the database, you'll need to re-upload them through the Angular interface.

## How It Works

### Upload Process:
1. User uploads file via Angular
2. Backend receives file
3. Backend creates directory: `sons/{type}/` (e.g., `sons/Ambiance/`)
4. Backend saves file as: `{name}_{timestamp}.{ext}` (e.g., `chant_mali_1234567890.mp3`)
5. Backend saves path in database: `sons\Ambiance\chant_mali_1234567890.mp3`

### Download Process:
1. Frontend requests: `GET /sounds/{id}/file`
2. Backend reads path from database
3. Backend loads file from disk
4. Backend returns file stream

### File Structure:
```
back/PEPs_back/sons/
├── README.md
├── Ambiance/
│   ├── chant_mali_1234567890.mp3
│   └── musique_foret_1234567891.wav
├── Vocal/
│   └── cri_perroquet_1234567892.mp3
└── Naturel/
    └── eau_1234567893.wav
```

## Testing

1. **Upload a sound:**
   - Open Angular app at http://localhost:4200
   - Navigate to sounds management
   - Upload an audio file
   - Check that file appears in `back/PEPs_back/sons/{type}/`

2. **Play a sound:**
   - Click play button on uploaded sound
   - Verify audio plays correctly

3. **Delete a sound:**
   - Delete a sound from the interface
   - Verify file is removed from `sons/{type}/` directory
   - Verify database record is deleted

## Benefits

✅ **Performance**: Database queries are faster without BYTEA fields  
✅ **Scalability**: File system handles large files better  
✅ **Maintainability**: Easy to backup, migrate, or inspect files  
✅ **Storage**: More efficient disk usage  

## Troubleshooting

### Files not uploading?
- Check write permissions on `back/PEPs_back/sons/` directory
- Check backend logs for errors
- Verify file format is supported (mp3, wav, ogg, m4a)

### Files not playing?
- Check that file exists at path stored in database
- Check file permissions (readable)
- Check browser console for errors

### Migration issues?
- Backup database before running migration
- Ensure no application is using the database during migration
- Check PostgreSQL logs for errors

## Need More Info?

See `MIGRATION_AUDIO_STORAGE.md` for complete technical details.
