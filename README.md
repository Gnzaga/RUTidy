# Front-end

## Run front-end locally 

1. Make sure you have node installed on your computer and change directory to "rutidy-client"

2. 
``` 
npm i 
```

3.
```
npm start
```

4. Visit localhost:3000 in your browser

## Push changes to VM

1. Build the front-end 
```
npm run build
```

2. Make sure you have read/write access to the directory /var/www/html

3. 
```
scp -r ./build/* netid@cs431-01.cs.rutgers.edu:/var/www/html
```

4. Make sure the user "www-data" has read/write access as well or else it will give you a 403 forbidden response