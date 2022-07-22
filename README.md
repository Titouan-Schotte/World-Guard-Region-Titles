# Introduction ...

It's a simple plugin that will allow you to configure custom titles to be sent to players when they enter a new world guard region.

# Examples :

Custom Region :
![image](https://user-images.githubusercontent.com/73279480/180415682-e7a3c10c-9a82-43ea-b276-6346d43cab13.png)

Default Region Example :
![image](https://user-images.githubusercontent.com/73279480/180415711-4da507d7-0d87-4ce0-ba81-920eb8d9c217.png)

Custom RPG Region :
![image](https://user-images.githubusercontent.com/73279480/180415745-4773adfa-0e53-430e-b15b-8c427c9ff082.png)


# Configuration :

For this there is a `region.txt` file that you will need to complete with the id of your world guard regions as well as the title to send to the players.

Likewise, there is a `config.json` file that will allow you to manage a default display of the player's region.

Minecraft color codes are enabled and will therefore allow you to design your title as you want.

Finally, you can include in your titles {playerName} and {regionId} which will be replaced by the real name of the player or the id of the world guard region.

# Dependencies :

- World Guard
- WGRegionEvents

# Commands :

/epickwgreload => reload config.json and region.txt

# Discord :
https://discord.gg/6ZpQmXZ2C5
