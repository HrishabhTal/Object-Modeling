package com.crio.jukebox.entities;


import java.util.List;
import java.util.stream.Collectors;


public class Song extends BaseEntity{
   private final String name;
   private final String album;
   private final List<String> artist;
   
   public Song(Song song){
    this(song.name,song.album,song.artist);
   }
   public Song(String id,String name,String album,List<String> artist){
    this(name,album,artist);
    this.id=id;
   }
   public Song(String name,String album,List<String> artist){
    this.name=name;
    this.album=album;
    this.artist=artist;
   }
   public String getName(){
    return name;
   }
   public String getAlbum(){
    return album;
   }
   public List<String> getArtist(){
    return artist.stream().collect(Collectors.toList());
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
       Song other = (Song) obj;
       if (id == null) {
           if (other.id != null)
               return false;
       } else if (!id.equals(other.id))
           return false;
       return true;
   }
   @Override
   public String toString() {
       return "Song [id=" + id  + ", name=" + name +", album="+album+", artist="+artist+"]";
   }
    
}
