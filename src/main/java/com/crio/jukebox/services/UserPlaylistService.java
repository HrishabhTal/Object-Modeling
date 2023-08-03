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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserPlaylistService implements IUserPlaylistService {
 
     private final IUserRepository userRepository;
     private final ISongRepository songRepository;
     private final IPlayListRepository playListRepository;
     
    
     public UserPlaylistService(IUserRepository userRepository, ISongRepository songRepository,IPlayListRepository playListRepository)
     {
          
        this.userRepository=userRepository;
        this.songRepository=songRepository;
        this.playListRepository=playListRepository;
    }

    @Override
    public User create(String name){
        return userRepository.save(new User(name));
    }
    @Override
    public String Loadsongs(String file){
        Path path=Paths.get(file);
        String line="";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path.toString()));
            while((line=br.readLine())!=null){
                String[] values=line.split("[,#]");
                List<String> artists=new ArrayList<String>();
                for(int i=4;i<values.length;i++){
                    artists.add(values[i]);
                }
                songRepository.save(new Song(values[0],values[2],artists));
            }
        } 
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "Songs Loaded successfully";
    }
    @Override
    public String CreatePlaylist(String Id,String name,List<String> songsId)throws SongNotFoundException{
        for(int i=0;i<songsId.size();i++){
            if(songRepository.SongIDexists(Id)==false){
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }
        PlayList q=playListRepository.save(new PlayList(name,Id,songRepository.SongIDwise(songsId)));
        return q.getId();
    }
    @Override
    public String DeletePlaylist(String userId,String playlistId)throws PlayListNotFoundException{
        if(playListRepository.deletePlaylist(userId, playlistId)){
            return "Delete Successful";

        }
        throw new PlayListNotFoundException("Playlist Not Found");


    }
    @Override
    public PlayList AddSong(String userId,String playlistId,List<String> songId)throws PlayListNotFoundException,SongNotFoundException{
        if(playListRepository.playlistexist(playlistId)==false){
            throw new PlayListNotFoundException("Playlist not found");
        }
        for(int i=0;i<songId.size();i++){
            if(songRepository.SongIDexists(songId.get(i))==false){
                throw new SongNotFoundException("Some Requested Songs Not Available.Please try again.");
            }
        }
        PlayList q=playListRepository.getPlaylist(playlistId);
        for(int i=0;i<songId.size();i++){
            if(q.Idexists(songId.get(i))==true){
                continue;
            }
            q.addSong(songRepository.getSongbyId(songId.get(i)));
        }
        return playListRepository.save(q);       

    }
    @Override
    public PlayList DeleteSong(String userId,String playlistId,List<String> songId)throws PlayListNotFoundException,SongNotFoundException{
        if(playListRepository.playlistexist(playlistId)==false){
            throw new PlayListNotFoundException("Playlist not found");
        }
        PlayList q=playListRepository.getPlaylist(playlistId);
        for(int i=0;i<songId.size();i++){
            if(q.Idexists(songId.get(i))==false){
              throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        for(int i=0;i<songId.size();i++){
            q.deleteSong(songRepository.getSongbyId(songId.get(i)));
        }
        return playListRepository.save(q);  
        
    }



    
}
