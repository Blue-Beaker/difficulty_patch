## Difficulty Patch
A patch mod for Minecraft that fixes a rare bug that messes up with difficulty setting in modpacks: If a mod load new dimensions before the world is loaded on client-side, World's difficulty can be overridden with the difficulty in client's settings(The one in options.txt), messing up difficulties.  
For example, putting ChickenChunks' chunk loader in GalactiCraft's Mars dimension can cause this problem.  
This mod fixes it by retrieving difficulty from overworld of the integrated server, which always get loaded, to fix the problem.  