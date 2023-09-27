# Assignment 3: Command Shell

## Requirements:

### List of File and Directories

list command

drwxr-xr-x 3 esperanzah esperanzah 4096 Sep  7 21:51 CS-3100
-rw-       1024 Jan 10, 2019 16:21 .test.text.swp

First four mean directory, user can read, user can write and user can execute.
Next 10 contain size of file in bytes, right justified, no commas.
Next is date of last modification, follow second example format.
Last field is name of file.

### Working Directory and Changing Directories

cd command

Only needs to allow for changing one directory at a time e.g. cd.. or cd src.
Return an error if directory does not exist.
Current directory is held by each process.

### Create and Remove Directories

mdir command
rdir command

if directory already exists/ name is already used, error message is displayed.
if directory does not exist for rdir, error message is displayed.

### Command History

history command
^ X command

Use arraylist to store commands
if user enters history, then entire history is displayed
if user uses ^ X display the Xth command using base 1 index

### Piping

| as separator

[/home/jdoe/Assign3/src]: cat Assign3.java | grep item.canRead
item.canRead() ? 'r' : '-',

### Exit

exit command

if user enters exit, then program will end


