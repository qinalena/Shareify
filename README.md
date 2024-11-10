# Shareify

Music social media app with Spotify Web API

This program is a social media platform that allows users to connect with others through their common interests in music.

When the user opens the app, they will be prompted to either sign up for an account by creating a username and password or log into their existing account.  Once logged in, the user will have access to their own account profile, where they can edit their username, and write a prompt for their bibliography/description section.  Their profile will display these features, as well as their friend list, playlist collection, and Spotify stats.  The Spotify stats will consist of their top five tracks, top artists, top genres, and the amount of minutes they’ve listened to music on the Spotify app.

Each user will have the ability to create their own playlist of soundtracks/songs, as well as selecting whether they want the playlist to be private or public, where the playlist can be shared amongst their friends.  Each playlist the user creates will be added to the user’s collection.  The user can also like and/or comment on the playlists of their friends, as well as add it to their own collection of playlists.  There will also be an option where the user can ask an AI to generate a playlist for them, it can be based on a specific genre or even based on their MBTI.  The user will be able to either add this generated playlist to their collection, or just simply discard it if they don’t like it.

Users will also have their own friends list, where they can add other users to this list or remove them from it.  Any users that are friends with each other will be able to view each other’s account profiles.

## Testing

The repo also includes an example of a use case interactor test, as well as
an example of an end-to-end test which automates button clicks and inspects
the contents of the actual views. This is something we discussed in the lectures
about testing in CA but had not provided a code example of before. Note, one
could also inspect the contents of the ViewModel objects instead when testing
CA to make a similar test which would be less dependent on the details of the
specific UI implementation.

## Project Starter Code

We used the Notes App as our starter code

## Team Members and Use Cases
Junyu Li - Signup / Login / Logout an account
Alena Qin - Creating and editing a playlist
Ashleigh Wong - View and manage a playlist collection
Aseef Ali Hasan - View and manage friends list
Don Edirisinghe - Interact with playlists in a friend’s playlist collection
