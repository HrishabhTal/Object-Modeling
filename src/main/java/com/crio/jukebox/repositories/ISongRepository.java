package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Song;
public interface ISongRepository {
    public Song save(Song entity);
    public boolean SongIDexists(String id);
    public List<Song> SongIDwise(List<String> idList);
    public Song getSongbyId(String songId);


    
}
