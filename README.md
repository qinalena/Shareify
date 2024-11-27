# Sharify

## Team: Pineapple
- ### Alena Qin (qinalena)
  - User Stories: Creating and editing a playlist
- ### Junyu Li (Jasjas7777)
  - User Stories: Signup/Login/Logout an account
- ### Don Prathap C. Edirisinghe (DonPrathap)
  - User Stories: Interact with playlists in a friend's playlist collection
- ### Aseef Ali Hasan (aseef2289)
  - User Stories: View and manage friends list
- ### Ashleigh Wong (Ash-uwu)
  - User Stories: View and manage a playlist collection
____
## Software Specification
- Domain: Social Media

This program is a social media platform that allows users to connect with
others through their common interests in music.

When the user opens the app, they will be prompted to either sign up for an account by creating a friendUsername and password
or log into their existing account.  Once logged in, the user will have access to their own account profile, where they
can edit their friendUsername, and write a prompt for their bibliography/description section.  Their profile will display these
features, as well as their friend list and playlist collection.

Each user will have the ability to create their own playlist of soundtracks/songs.  Each playlist the user creates will
be added to the user’s collection.  The user can also like and/or comment on the playlists of their friends.

Users will also have their own friends list, where they can add other users to this list or remove them from it.  
Any users that are friends with each other will be able to view each other’s account profiles.
____
## User Stories
### Customize and manage an account profile
Users have an account profile which will display basic personal information (friendUsername and bio), their friends list,
and their playlist collection. Users can write and edit a custom bio. ***[Team]***

### Signup / Login / Logout an account
Users can sign up for an account by creating a friendUsername and password. The app will show the “This friendUsername is taken”
to avoid repetition in friendUsername if the user creates a friendUsername that already exists. They could log in to or log out
their account. A new account starts with an empty playlist collection. ***[Junyu Li]***

### Creating and editing a playlist
Users can search for tracks by inputting the track’s title or artist names. 
Tracks can be added or removed from a playlist. ***[Alena Qin]***

### View and manage a playlist collection
As a user, they want to be able to create, view, and manage the playlists they have created.  This can be done by 
viewing the collections section of the program, displaying all playlists or asking the user to add a new playlist. 
They will be able to keep their playlists private,  public -  allowing them to share playlists with their friends. 
***[Ashleigh Wong]***

### View and manage friends list
Users can view their friends list which is linked to their profile. Friends can be added and removed. Users can also
view their friends’ account profiles. ***[Aseef Ali Hasan]***

### Interact with playlists in a friend's playlist collection
Users can like and comment on playlists in their friends’ playlist collections. They can also add their friends’
playlists to their own playlist collection. ***[Don Edirisinghe]***
----
## Entities
- User
  - name
  - password
  - info
  - playlistCollection
  - friendsList
- Tracks
  - Name
  - Artist
  - Genre
- Playlist
  - Name
  - Tracks
  - Likes
  - Comments
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
- Like playlist
- Comment playlist
____
## API
- Spotify Web API: https://developer.spotify.com/documentation/web-api/reference/get-an-album

-> Provides access to songs, albums, and genres of songs within Spotify
