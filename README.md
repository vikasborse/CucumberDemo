# UITestsBelong

***Coding assignment - Cucumber scenario***
Write feature file , step definitions and pages for any two of the below requirements 

Site URL: http://automationpractice.com/

1.	While registering, if the email address is incorrect. User should see an error message
2.	Check whether Megamenu works (Dresses -> Summer dresses)
3.	On the Summer dresses page, select a dress(Quick view) and change the color (Blue) and then add the item to cart, Validate	whether same product name, color and quantity are displayed in Cart Summary page.

Once you finish assignment, please raise a PR on develop. 

If you have write access issues, please push changes to your personal account and share.


## Solution:

  1. While registering, if the email address is incorrect. User should see an error message
    -   Run validation features
    ```
    mvn clean compile integration-test -Dcucumber.options="--tags @validations"
    ```

  2.  Check whether Megamenu works (Dresses -> Summer dresses)
      -   Run Megamenu features
  
    ```
    mvn clean compile integration-test -Dcucumber.options="--tags @navigation"
    ```
  3 . On the Summer dresses page, select a dress(Quick view) and change the color (Blue) and then add the item to cart, Validate	whether same product name, color and quantity are displayed in Cart Summary page.
      -   Run cart features
     ```
     mvn clean compile integration-test -Dcucumber.options="--tags @cart"
     ```     
      
# Run all features
    ```
    mvn clean compile integration-test -Dcucumber.options="--tags @regression"
    ```

 - Created test for adding Blue colored product 'Printed Summer Dress' to the cart from quick view.
 The test can bee run with any product name, quantity and color. As the product 'Printed Summer Dress' is available twice , one with and other without color, the test iterates through all the products in Summer dresses to find the dress with Blue color.
    

## Run with docker image in build pipeline:
1. Use Dockerfile to install the chrome and chromedriver in docker image and push the image to docker repo. Copy docker image name <account_name/docker_image_name>.
2. Add steps in pipeline to use docker image

- Build docker 
```
docker build . -t <account_name/docker_image_name>
```

- Run Cucumber features with Maven in docker image
```
run -w /usr/src <account_name/docker_image_name> mvn clean compile integration-test -Dcucumber.options="--tags @validations"
```

## Run with build agent in build pipeline:
1. Git checkout task
2. Maven task

```
 mvn clean compile integration-test -Dcucumber.options="--tags @validations"
```


 
