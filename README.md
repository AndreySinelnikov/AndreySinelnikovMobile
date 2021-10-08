#### HOMEWORK 2

Launch native app test via terminal command:

`mvn clean test -PhomeworkNative`

Launch web app test via terminal command:

`mvn clean test -PhomeworkWeb`

---------------

#### HOMEWORK 3

Before launching any tests, add EPAM mobile cloud API key as `apiKey` property in *src/test/resources/test.properties*.

In any of the following terminal commands to launch tests, replace 'SERIAL' with 
the value of 'Serial' column of the selected cloud device.

Launch cloud Android web app test:

`mvn clean test -PcloudAndroidWeb -Dudid=SERIAL`

Launch cloud iOS web app test:

`mvn clean test -PcloudIosWeb -Dudid=SERIAL`

Launch cloud Android native app test:

`mvn clean test -PcloudAndroidNative -Dudid=SERIAL`

Launch cloud iOS native app test:

`mvn clean test -PcloudIosNative -Dudid=SERIAL`