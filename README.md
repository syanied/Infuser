# Infuser [x1.0]

Desktop (win 7/10/11) release: [here ](https://discord.gg/d9rFtgkBXK)

[This application uses a modular system in the form of plugins that can be developed via its API]

[IMPORTANT]: Infuser version x1.0 does NOT have a controlled environment such as a Sandbox
                     to limit the permission(s) of the Plugin used during application runtime. 
# API Implementation

[**>**] JAR builds in the artifacts folder can be used in your project as external dependency to develop Plugins for Infuser.

[**>**] File ("PluginInfo.ins" [**Case-Sensitive**]) mentioning the Class implementing the `run()` method form the interface class `InfuserPlugin` (moslty the Main class of your Plugins) should be present in the '*src*' folder of your Project for the Plugin loader of the application to safely load the Plugin.

**Inside "PluginInfo.ins" File:**

```
Author: { me.author }
Main: { package.mainclass } // correct path must be specified.
Version: { plugin version }
Description: { a brief description of your plugin }
