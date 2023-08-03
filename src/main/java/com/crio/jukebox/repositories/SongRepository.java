package com.crio.jukebox.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;


public class SongRepository implements ISongRepository {
    
    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        songMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Song q = new Song(Integer.toString(autoIncrement),entity.getName(),entity.getAlbum(),entity.getArtist());
            songMap.put(q.getId(),q);
            return q;
        }
        songMap.put(entity.getId(),entity);
        return entity;
    }
 
    @Override
    public boolean SongIDexists(String id){
        return songMap.containsKey(id);
    }
    @Override
    public List<Song> SongIDwise(List<String> idList){
    List<Song> songList = songMap.values().stream().filter(song -> idList.contains(song.getId())).collect(Collectors.toList());
    return songList;
    }
    @Override
    public Song getSongbyId(String songId){
        return songMap.get(songId);
    }
    
}
