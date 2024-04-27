# timesync-plugin
A drop-in plugin for synchronising your server's time to real-time.

timesync-plugin is a Minecraft Paper Plugin (developed for 1.19 and up) which synchronises the server's time to the real-time (this depends on the servers time settings and location). 
It uses Bukkits EventScheduler to schedule itself. Parts of the plugin are written in Kotlin.
## Requirements
The plugin was tested for minecraft paper 1.19 and newer. It should also work on Spigot, and on some older Paper versions.
## Installation
Head over to the releases-tab and grab the latest full release! To install the plugin, just put it inside you Paper server's plugin directory. 
Additionally, it is recommended to disable day-night cycle on your minecraft server for the best experience.

## Usage
While the plugin does most things itself, there is a `/timesync` command to check the plugins' configuration. 
It will display the system time of the server and the in-game-time of the server.

## Known limitations
These are the things that are currently being worked on:
- No option for setting the time zone
  - This means that the server time syncs itself to the servers' time, which may be not optimal if a hosting provider in another country/timezone is being used
- No option for custom sync delay
- No possibility to make the day longer and the nights shorter
- The plugin does not account for summer/winter time
