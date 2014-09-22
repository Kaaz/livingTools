To install  

1. Download 1.7.10-Recommended (Src) from -> http://files.minecraftforge.net/
2. Copy the contents to a decent directory
3. Open console in that directory
4. enter the command: gradlew setupDecompWorkspace --refresh-dependencies
5. Fix your IDE. For Intellij see below; for other IDE's seehttp://www.minecraftforge.net/wiki/Installation/Source


For Intellij only:

1. Open intellij
2. Import project and navigate to the workspace and click the build.gradle
3. close intellij
4. open the console in the root directory
5. enter the command: gradlew genIntellijRuns