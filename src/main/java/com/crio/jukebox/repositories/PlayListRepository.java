package com.crio.jukebox.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.PlayList;


public class PlayListRepository implements IPlayListRepository {
    private final Map<String,PlayList> playlistMap;
    private Integer autoIncrement = 0;

    public PlayListRepository(){
        playlistMap = new HashMap<String,PlayList>();
    }

    public PlayListRepository(Map<String,PlayList> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    @Override
    public PlayList save(PlayList entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            PlayList q = new PlayList(Integer.toString(autoIncrement),entity.getName(),entity.getUser(),entity.getSongs());
            playlistMap.put(q.getId(),q);
            return q;
        }
        playlistMap.put(entity.getId(),entity);
        return entity;
    }
    public List<Song> Songlist(String userID, String playlistID ){
        if(playlistMap.containsKey(playlistID)){
            PlayList p=playlistMap.get(playlistID);
            if(p.getId().equals(userID)){
                return p.getSongs();
            }
        }
        return List.of();
    }
    @Override
    public boolean deletePlaylist(String userId,String playlistId){
        return playlistMap.containsKey(playlistId)?playlistMap.get(playlistId).getUser().equals(userId)?true:false:false;
   }
   @Override
   public boolean playlistexist(String ID){
    return playlistMap.containsKey(ID);
   }
   @Override
   public PlayList getPlaylist(String playlistId){
    return playlistMap.get(playlistId);
   } 

    
}
