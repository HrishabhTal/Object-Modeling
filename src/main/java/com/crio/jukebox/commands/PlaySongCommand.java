package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IUserSongService;

public class PlaySongCommand implements ICommand {
    private final IUserSongService userSongService;

    public PlaySongCommand(IUserSongService userSongService)
    {
        this.userSongService=userSongService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String op=tokens.get(2);
        if(op.equals("NEXT")){
          
                Song currsong=userSongService.Next(userId);
                System.out.println("Current Song Playing");
                System.out.println("Song - "+currsong.getName().trim());
                System.out.println("Album - "+currsong.getAlbum().trim());
                System.out.println("Artists - "+currsong.getArtist().toString().replace("[", "").replace("]", "").replace(", ",",").trim());
        }
        if(op.equals("BACK")){
           
                Song currsong=userSongService.Back(userId);
                System.out.println("Current Song Playing");
                System.out.println("Song - "+currsong.getName().trim());
                System.out.println("Album - "+currsong.getAlbum().trim());
                System.out.println("Artists - "+currsong.getArtist().toString().replace("[", "").replace("]", "").replace(", ",",").trim());
            
        }
        if(op.equals("NEXT")==false && op.equals("BACK")==false){
            try{
                Song currsong=userSongService.playbyId(userId,op);
                System.out.println("Current Song Playing");
                System.out.println("Song - "+currsong.getName().trim());
                System.out.println("Album - "+currsong.getAlbum().trim());
                System.out.println("Artists - "+currsong.getArtist().toString().replace("[", "").replace("]", "").replace(", ",",").strip().trim());
            }catch(RuntimeException e)
            {
                System.out.println(e.getMessage());
            } 
        }
        
    }
    
    
}
