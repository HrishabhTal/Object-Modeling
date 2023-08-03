package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IUserPlaylistService {
    public User create(String name);
    public String Loadsongs(String file);
    public String CreatePlaylist(String Id,String name,List<String> songsId)throws SongNotFoundException;
    public String DeletePlaylist(String userId,String playlistId)throws PlayListNotFoundException;
    public PlayList AddSong(String userId,String playlistId,List<String> songId)throws PlayListNotFoundException,SongNotFoundException;
    public PlayList DeleteSong(String userId,String playlistId,List<String> songId)throws PlayListNotFoundException,SongNotFoundException;
    
}
