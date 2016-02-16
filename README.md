[![Build Status](https://travis-ci.org/angelndevil2/system-info.svg?branch=develop)](https://travis-ci.org/angelndevil2/system-info)

#system-info

## make zip distribution

        ./gradlew distZip

[download binary zip](http://search.maven.org/remotecontent?filepath=com/tistory/devilnangel/system-info/0.0.1/system-info-0.0.1.zip) from http://search.maven.org

## run

        /path/to/system-info-0.0.1/bin/launch
        
        -h option will print help message

## register to local maven

        gradlew or gradlew.bat uploadArchives

## run in rmi server mode with default port (1099)

        /path/to/system-info-0.0.1/bin/launch -s
        
## run in rmi server mode with port 10999

        /path/to/system-info-0.0.1/bin/launch -s -p 10999
        
## java ex

### server
```java
RmiSystemInfoServer s = new RmiSystemInfoServer();
System.setProperty("java.rmi.server.hostname", "localhost");
s.startRmiServer();
```
### client
```java
RmiSystemInfoClient sic = new RmiSystemInfoClient("localhost");
System.out.println(sic.getCpuInfo().getCpuBusy());

System.out.println(sic.getUlimitInfo().getOpenFilesHardLimit());
```
        
