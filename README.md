# timesync-plugin
Fast and lightweight time synchronization for your Minecraft server

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
As a Minecraft day is the same lenght every day, this plugin does not account for seasonal and locational sunrise changes. Instead, this emulates an idealized day.

# Development Info
Development on this plugin is mostly finished.
