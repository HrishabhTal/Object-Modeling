package com.crio.jukebox.entities;


import java.util.List;
import java.util.stream.Collectors;
public class PlayList extends BaseEntity {
    private final String name;
    private final String user;
    private List<Song> songs;
    

    public PlayList(PlayList playlist){
        this(playlist.id,playlist.name,playlist.user,playlist.songs);
    }
    public PlayList(String id,String name,String user,List<Song> songs){
        this(name,user,songs);
        this.id=id;
    }
    public PlayList(String name,String user,List<Song> songs){
        this.name=name;
        this.user=user;
        this.songs=songs;
    }
    public String getName(){
        return name;
    }
    public String getUser(){
        return user;
    }
    public List<Song> getSongs(){
        return songs.stream().collect(Collectors.toList());
    }
   
    public void addSong(Song song){
        songs.add(song);
    }

    public void deleteSong(Song song){
        songs.removeIf(c -> c.getId() == song.getId());
    }
    public List<String> getSongId(){
        return songs.stream().map(e->e.getId()).collect(Collectors.toList());
    }
    public boolean Idexists(String Id){
        return songs.stream().anyMatch(e->e.getId().equals(Id));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayList other = (PlayList) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "PlayList [id=" + id + ", name=" + name + ", user=" + user + ", songs=" + songs + "]";
    }



    
}
