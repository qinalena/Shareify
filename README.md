# Note Application

This is a minimal example demonstrating usage of the
password-protected user part of the API used in lab 5.

You can find more information about the API endpoints in
[the documentation](https://www.postman.com/cloudy-astronaut-813156/csc207-grade-apis-demo/documentation/fg3zkjm/5-password-protected-user).

If your team is considering an application for which it would be convenient to
store data in something like a database, you may find that the API calls demonstrated
here will be useful in your project, as this will allow you to store
an arbitrary JSON object associated with a username and password.

In this application, a single note has a name (the "username" in terms of the API) and the note
can be read by anyone who knows the name — but only edited by someone who
knows the password for it.

You can see the documentation in the various files for more information.

## Testing

The repo also includes an example of a use case interactor test, as well as
an example of an end-to-end test which automates button clicks and inspects
the contents of the actual views. This is something we discussed in the lectures
about testing in CA but had not provided a code example of before. Note, one
could also inspect the contents of the ViewModel objects instead when testing
CA to make a similar test which would be less dependent on the details of the
specific UI implementation.

## Project Starter Code

Your team may choose to use this repo as starter code for your project. You could
also use the lab 5 code — or start from an empty repo if your team prefers.

If you choose to use one of the repositories we have provided, you can either make
a fork of it or copy the subset of code you want into a completely new repository.
______
# Sharify

## Team: Pineapple
- ### Alena Qin (qinalena)
  - User Stories: Searching and adding songs to playlist
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

When the user opens the app, they will be prompted to either sign up for an account by creating a username and password 
or log into their existing account.  Once logged in, the user will have access to their own account profile, where they 
can edit their username, and write a prompt for their bibliography/description section.  Their profile will display these 
features, as well as their friend list and playlist collection. 

Each user will have the ability to create their own playlist of soundtracks/songs.  Each playlist the user creates will 
be added to the user’s collection.  The user can also like and/or comment on the playlists of their friends.

Users will also have their own friends list, where they can add other users to this list or remove them from it.  
Any users that are friends with each other will be able to view each other’s account profiles.
____

## User Stories
### Customize and manage an account profile 
Users have an account profile which will display basic personal information (username and bio), their friends list, 
and their playlist collection. Users can write and edit a custom bio. ***[Team]***

### Signup / Login / Logout an account
Users can sign up for an account by creating a username and password. The app will show the “This username is taken” 
to avoid repetition in username if the user creates a username that already exists. They could log in to or log out 
their account. A new account starts with an empty playlist collection. ***[Junyu Li]***

### View and manage a playlist collection
As a user, they want to be able to create, view, and manage the playlists they have created.  This can be done by 
viewing the collections section of the program, displaying all playlists or asking the user to add a new playlist.  
In addition to creating a playlist within the playlist collection, they can add their preferred soundtracks or songs to 
the playlist they have created. ***[Ashleigh Wong]***

### Searching and adding songs to playlist
Users will be able to search for their favourite songs by inputting the title of the song, as well as the name of the 
artist.  Once the selected song is found, the user will be able to add the song to their preferred playlist within
collection of playlists. ***[Alena Qin]***

### View and manage friends list
Users can view their friends list which is linked to their profile. Friends can be added and removed. Users can also 
view their friends’ account profiles. ***[Aseef Ali Hasan]***

### Interact with playlists in a friend's playlist collection
Users can like and comment on playlists in their friends’ playlist collections. They can also add their friends’ 
playlists to their own playlist collection. ***[Don Edirisinghe]***
----
## Entities
- User
  - username
  - password
  - bio
  - playlistCollection
  - friendsList
- Tracks
  - name
  - artist
  - genre
- Playlist
  - name
  - tracks
  - likes
  - comments
____
## Use Cases
- Create Account
  - Sign up
- Customizing account
  - Creating bio
- Login user
- Logout user
- Create Playlist
  - Edit Playlist
  - Delete Playlist
  - Open Playlist
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