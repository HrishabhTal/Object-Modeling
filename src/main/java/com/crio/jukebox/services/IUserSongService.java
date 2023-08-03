package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface IUserSongService {
    public Song PlayPlaylist(String userId,String playlistId)throws PlayListNotFoundException;
    public Song Next(String userId);
    public Song Back(String userId);
    public Song playbyId(String userId,String songId)throws SongNotFoundException;    
}
