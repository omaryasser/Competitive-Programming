string ss;
getline(cin,ss);
strcpy(a,ss.c_str());
char *token = strtok(a, "* ");
while(token !=NULL){
	printf("%s\n",token);
	token=strtok(NULL,"* ");
}
