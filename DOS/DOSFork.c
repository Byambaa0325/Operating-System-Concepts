#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>


char ** splitStr(char * str, int size) {
    str[strlen(str) - 1] = '\0';
    char * ptr = strtok(str, " ");
    int i;
    char ** args[size];
    
	for (i = 0; i < size; i++) {
        args[i] = ptr;
        ptr = strtok(NULL, " ");
    }
    
	return args;

}
void dir() {
    struct dirent * de;
    
	DIR * dr = opendir(".");
    if (dr == NULL) {
        printf("Unable to open current directory \n");
        return;
    }
    
	while ((de = readdir(dr)) != NULL) {
        printf("%s\n", de -> d_name);
    }
    closedir(dr);
}
void ren(char * arr) {
    int result;
    char ** args = splitStr(arr, 3);
    
	char * oldname = args[1];
    char * newname = args[2];
    
	result = rename(oldname, newname);
    if (result == 0)
        orintf("File successfully renamed\n");
    else
        perror("Error renaming file");
}
void create(char * arr) {
    char temp[50];
    strcpy(temp, arr);
    
    char ** args = splitStr(arr, 3);
    char * filename = args[1];
    
	FILE * out = fopen(filename, "w");
    fprintf(out, temp + 6 + strlen(filename) + 2);
    fclose(out);
    
	printf("File successfully created\n");
}
void input(char * arr) {
    char temp[50];
    strcpy(temp, arr);
    
	char ** args = splitStr(arr, 3);
    char * filename = args[1];
    
	FILE * out = fopen(filename, "w");
    if (out == NULL) {
        printf("File does not exists \n");
        return;
    }
    fprintf(out, temp + 6 + strlen(filename) + 1);
    fclose(out);
    
	printf("File contents successfully changed\n");
}
void del(char * arr) {
    char ** args = splitStr(arr, 2);
    char * file_name = args[1];
    
	int status = remove(file_name);
    if (status == 0)
        printf("%s file deleted successfully.\n", file_name);
    else {
        printf("Unable to delete the file\n");
        perror("Following error occurred");
    }
}

int main() {
    char arg[50];
    printf("Starting DOS...\n");
    while (1) {
        printf(">>>");
        fgets(arg, 50, stdin);
        if (strncmp(arg, "dir", 3) == 0) {
        	if(fork()==0){
        		dir();
        		return 0;
			}
			else{			 
            	continue;	
			}           
        }
        if (strncmp(arg, "create", 6) == 0) {
            if(fork()==0){
        		create(arg);
        		return 0;
			}
			else{			 
            	continue;	
			} 
        }
        if (strncmp(arg, "input", 5) == 0) {
            if(fork()==0){
        		input(arg);
        		return 0;
			}
			else{			 
            	continue;	
			} 
        }
        if (strncmp(arg, "rename", 6) == 0) {
            if(fork()==0){
        		ren(arg);
        		return 0;
			}
			else{			 
            	continue;	
			} 
        }
        if (strncmp(arg, "delete", 6) == 0) {
            if(fork()==0){
        		del(arg);
        		return 0;
			}
			else{			 
            	continue;	
			} 
        }
        if (strncmp(arg, "exit", 4) == 0) {
            printf("Exiting DOS...");
            break;
        }
    }
    return 0;
}
