package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;

public interface IPlayListRepository {
    public PlayList save(PlayList entity);
    public List<Song> Songlist(String userID, String playlistID );
    public boolean deletePlaylist(String userId,String playlistID);
    public boolean playlistexist(String ID);
    public PlayList getPlaylist(String playlistId);
    
}
