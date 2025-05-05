@echo off

for %%I in ("%~dp0.") do for %%J in ("%%~dpI.") do set PARENT_DIR_NAME=%%~nxJ
echo Configuring your local Git repository (%PARENT_DIR_NAME%) with configurations, aliases and hook scripts

git config --replace-all include.path "../git-config/.gitconfig"
git config --add include.path "../git-config/.gitalias"

echo:
echo Configuration successful!
echo Press any key to exit...
pause > nul
