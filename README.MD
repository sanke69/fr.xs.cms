JAVA CMS
========

How to install it
-----------------

In order to be compiled or used as dependency in your project, this project needs that you first retrieve the main POM which is used as parent and the JTK project which this one depends on.

```
cd <where_you_want>
git clone https://github.com/sanke69/fr.xs.pom.git
cd fr.xs.pom
mvn install
```  
then
```
cd ..
git clone https://github.com/sanke69/fr.xs.jtk.git
cd fr.xs.jtk
mvn install
```  
then
```
cd ..
git clone https://github.com/sanke69/fr.xs.cms.git
cd fr.xs.cms
mvn install
```  

About this project
------------------

Mainly a Spring lite, which AJAX support and HTML5 objects.
