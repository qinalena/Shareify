# Sharify

## Team: Pineapple
- ### Alena Qin (qinalena)
  - User Stories: Search songs, add/remove songs to/from a playlist
- ### Junyu Li (Jasjas7777)
  - User Stories: Signup/Login/Logout an account
- ### Don Prathap C. Edirisinghe (DonPrathap)
  - User Stories: Interact with friends through chat feature and commenting on their playlists
- ### Aseef Ali Hasan (aseef2289)
  - User Stories: View and manage friends list
- ### Ashleigh Wong (Ash-uwu)
  - User Stories: View and manage a playlist collection
____
## Software Specification
- Domain: Social Media

This program is a social media platform that allows users to connect with
others through their common interests in music.

When the user opens the app, they will be prompted to either sign up for an account by creating an account with a username and password
or log into their existing account.  Once logged in, the user will have access to their own user profile, where a default bio is displayed. They
can write a new bio, change their password, and view their friends list and playlist collection.

In a user's playlist collection, they can add or remove playlists. Opening a specific playlist displays the songs stored. 
The user can remove songs or add new songs using a search function. The user can also comment on the playlists of their friends.

Users will also have their own friends list, where they can add other users to this list or remove them from it.  
Any users that are friends will be able to view each other’s account profiles and chat with each other.
____
## User Stories
### Customize and manage an account profile
Users have a user profile which will display basic personal information (username and bio), their friends list,
and their playlist collection. Users can write and edit a custom bio. ***[Team]***

### Signup / Login / Logout an account
Users can sign up for an account by creating a friendUsername and password. The app will show the “This User is taken”
to avoid repetition in usernames if the user creates a user with a username that already exists. They could log in to or log out
their account. A new account starts with an empty playlist collection. ***[Junyu Li]***

### View and manage a playlist collection
As a user, they want to be able to create, view, and manage the playlists they have created.  This can be done by 
viewing the collections section of the program, displaying all playlists or asking the user to add a new playlist. A new playlist will have no songs.
They will be able to keep their playlists private,  public -  allowing them to share playlists with their friends. 
***[Ashleigh Wong]***

### Creating and editing a playlist
Users can open playlists in their playlist collection, displaying any previously added songs. 
Users can remove songs or add new songs using the search function, which will display the 10 most relevant songs to the query. 
***[Alena Qin]***

### View and manage friends list
Users can view their friends list which is linked to their profile. Friends can be added and removed. Users can also
view their friends’ account profiles. ***[Aseef Ali Hasan]***

### Interact with friends
Users can message their friends by viewing a friend from the User's friend list, inputting a message, and sending it. 
Users can also view their friend's playlists and comment on them, being able to see everyone else's comments on that 
playlist aswell. ***[Don Edirisinghe]***
----
## Entities
- User
  - username
  - password
  - note
  - info
- Tracks
  - Name
  - Artist
  - Genre
- Playlist
  - Name
  - Tracks
____
## Use Cases
- Sign up
- Login
- Logout
- Note
- Change Password
- Playlist Collection
  - Add Playlist
- Add songs
  - Search for songs
- Create friend list
  - Add friends
- Message friend
- Comment on friend's playlists
____
## API
- Spotify Web API: https://developer.spotify.com/documentation/web-api/reference/get-an-album

-> Provides access to songs, albums, and genres of songs within Spotify
