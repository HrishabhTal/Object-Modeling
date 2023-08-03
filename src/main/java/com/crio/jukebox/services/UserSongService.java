package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.PlayList;
import java.util.ArrayList;
import java.util.List;
public class UserSongService implements IUserSongService {
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
    private final IPlayListRepository playListRepository;
   
    public UserSongService(IUserRepository userRepository, ISongRepository songRepository,IPlayListRepository playListRepository)
    {
         
       this.userRepository=userRepository;
       this.songRepository=songRepository;
       this.playListRepository=playListRepository;
   }
   private Song currentsong;
   private int currentindex;
   private String currentplaylistId;
   @Override
   public Song PlayPlaylist(String userId,String playlistId)throws PlayListNotFoundException{
    List<Song> music=playListRepository.Songlist(userId, playlistId);
    currentplaylistId=playlistId;
    if(music.isEmpty()){
        throw new PlayListNotFoundException("Playlist is empty.");
    }
    currentsong=music.get(0);
    currentindex=0;
    return music.get(0);

   }
   @Override
   public Song Next(String userId){
    List<Song> music=playListRepository.Songlist(userId,currentplaylistId);
    int index=music.indexOf(currentsong);
    if(index==music.size()-1){
        currentsong=music.get(0);
        currentindex=0;
        return music.get(0);

    }
    currentsong=music.get(index+1);
    currentindex=index+1;
    return currentsong;
   }
   @Override
   public Song Back(String userId){
    List<Song> music=playListRepository.Songlist(userId,currentplaylistId);
    int index=music.indexOf(currentsong);
    if(index==0){
        currentsong=music.get(music.size()-1);
        currentindex=music.size()-1;
        return music.get(music.size()-1);
    }
    currentsong=music.get(index-1);
    currentindex=index-1;
    return currentsong;
   }
   @Override
   public Song playbyId(String userId,String songId)throws SongNotFoundException{
    List<Song> music=playListRepository.Songlist(userId,currentplaylistId);
    Song song=music.stream().filter(e->e.getId().equals(songId)).findAny().orElse(null);
    if(song==null)
    {
        throw new SongNotFoundException("Given song id is not a part of the active playlist");
    }
    int index=music.indexOf(song);
    currentsong=music.get(index);
    currentindex=index;
    return music.get(index);


   }


    
}
