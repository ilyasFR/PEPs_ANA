# Audio Storage Configuration

## Issue: Files Saved to Wrong Location

By default, the application saves files relative to the Tomcat working directory (usually `tomcat/bin/sons`), not the project directory.

## Solution: Set Environment Variable

Set the `PEPS_AUDIO_DIR` environment variable to specify where audio files should be stored.

### Option 1: Set in Tomcat (Recommended)

**Windows - Edit `catalina.bat`:**
Add this line before the last line:
```bat
set PEPS_AUDIO_DIR=D:\Users\Ilyas\Desktop\Me\Codes\Vscode\Projects\PEPs\back\PEPs_back\sons
```

**Location:** `apache-tomcat-9.0.111\bin\catalina.bat`

### Option 2: Set in NetBeans

1. Right-click your project → Properties
2. Go to Run → VM Options
3. Add: `-DPEPS_AUDIO_DIR=D:\Users\Ilyas\Desktop\Me\Codes\Vscode\Projects\PEPs\back\PEPs_back\sons`

### Option 3: System Environment Variable

1. Windows Search → "Environment Variables"
2. Add new system variable:
   - Name: `PEPS_AUDIO_DIR`
   - Value: `D:\Users\Ilyas\Desktop\Me\Codes\Vscode\Projects\PEPs\back\PEPs_back\sons`
3. Restart Tomcat/NetBeans

### Option 4: Use Default Tomcat Location

If you don't set `PEPS_AUDIO_DIR`, files will be saved to:
```
{TOMCAT_HOME}\webapps\audio\sons\
```

Example: `D:\Users\Ilyas\Desktop\Me\App\apache-tomcat-9.0.111\webapps\audio\sons\`

## Current Situation

Your files are currently in:
```
D:\Users\Ilyas\Desktop\Me\App\apache-tomcat-9.0.111\bin\sons\
```

You can either:
1. **Move them** to the project directory after setting the environment variable
2. **Keep using** the Tomcat directory (configure path accordingly)

## After Configuration

1. Restart Tomcat/NetBeans
2. Upload a test file
3. Verify it appears in the correct directory
4. Update database paths if needed

## Migration Script (if moving files)

If you want to move existing files from Tomcat to project directory:

```powershell
# PowerShell script
$source = "D:\Users\Ilyas\Desktop\Me\App\apache-tomcat-9.0.111\bin\sons"
$dest = "D:\Users\Ilyas\Desktop\Me\Codes\Vscode\Projects\PEPs\back\PEPs_back\sons"

Copy-Item -Path $source\* -Destination $dest -Recurse -Force
```

Then update the database paths:
```sql
UPDATE public.Sound 
SET chemin = REPLACE(chemin, 
    'D:\Users\Ilyas\Desktop\Me\App\apache-tomcat-9.0.111\bin\sons',
    'D:\Users\Ilyas\Desktop\Me\Codes\Vscode\Projects\PEPs\back\PEPs_back\sons');
```
