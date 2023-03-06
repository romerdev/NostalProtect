# NostalProtect

 Instantly protect your Minecraft world with easy to use permissions! Such as:
 
 - `np.block.STONE.place`
 - `np.item.GRASS.pickup`
 - `np.entity.ARMOR_STAND.interact`
 
 And with the [Worldguard integration](https://github.com/romerdev/NostalProtect#worldguard-region-integration) you could even specify the region.


## Installation

Place this plugin and a permission manager, such as [Luckperms](https://luckperms.net), in your plugin folder and (re)start the server. Your world is now protected! That was easy, huh?

Now you can give groups or players the permissions you want.


## Permission usage

- Block place: `np.block.<material>.place`
- Block break: `np.block.<material>.break`
- Block interact: `np.block.<material>.interact`
- Entity spawn: `np.entity.<type>.place`
- Entity break: `np.entity.<type>.break`
- Entity interact: `np.entity.<type>.interact`
- Entity damage: `np.entity.<type>.damage`
- Item use: `np.item.<material>.use`
- Item drop: `np.item.<material>.drop`
- Item pickup: `np.item.<material>.pickup`

Visit the [Permissions page](https://github.com/romerdev/NostalProtect/wiki/Permissions) in the Wiki for more specific permissions such as `np.entity.ITEM_FRAME.rotate`.


 ## WorldGuard Region integration
 
 I'm currently adding a Worldguard integration that will allow region based protection permissions:
 
 - `np.<type>.<material>.<action>.own `: Allow this action on regions where the player is a member or owner.
 - `np.<type>.<material>.<action>.global`: Allow this action on global region.
 - `np.<type>.<material>.<action>.<region>`: Allow this action on a specific region
