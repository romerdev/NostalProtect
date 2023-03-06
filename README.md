# NostalProtect
 Instantly protect your Minecraft world with easy to use permissions! Such as:
 
 - np.block.STONE.place
 - np.item.GRASS.pickup
 - np.entity.ARMOR_STAND.interact
 
 ### WIP
 
 I'm currently adding a Worldguard integration that will allow region based protection permissions:
 
 - `np.<type>.<material>.<action>.*own* `: Allow this action on regions where the player is a member or owner.
 - `np.<type>.<material>.<action>.*global*`: Allow this action on global region.
 - `np.<type>.<material>.<action>.*<region>*`: Allow this action on a specific region

## Installation
Place this plugin and permission manager, such as Luckperms, in your plugin folder and (re)start the server. Your world is now protected! That was easy, huh?

Now you can give groups or players the permissions you want.

## Permission usage

- Block place: `np.block.<material>.place`
- Block break: `np.block.<material>.remove`
- Block interact: `np.block.<material>.interact`
- Entity spawn: `np.entity.<type>.place`
- Entity destroy: `np.entity.<type>.remove`
- Entity interact: `np.entity.<type>.interact`
- Entity damage: `np.entity.<type>.damage`
- Item use: `np.item.<material>.use`
