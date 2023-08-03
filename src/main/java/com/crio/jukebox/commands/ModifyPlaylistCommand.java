package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.services.IUserPlaylistService;

public class ModifyPlaylistCommand implements ICommand {
    private final IUserPlaylistService userPlaylistService;

    public ModifyPlaylistCommand(IUserPlaylistService userPlaylistService)
    {
        this.userPlaylistService=userPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String op=tokens.get(1);
        String userId=tokens.get(2);
        String playlistId=tokens.get(3);
        List<String> songsId=new ArrayList<String>();
        for(int i=4;i<tokens.size();i++){
            songsId.add(tokens.get(i));
        }
        if(op.equals("ADD-SONG")){
            try{
                PlayList songs=userPlaylistService.AddSong(userId, playlistId, songsId);
                System.out.println("Playlist ID - "+songs.getId().trim());
                System.out.println("Playlist Name - "+songs.getName().trim());
                System.out.println("Song IDs - "+songs.getSongId().toString().replace(",", "").replace("[", "").replace("]", "").trim());
            }catch(RuntimeException e)
            {
                System.out.println(e.getMessage()); 
            }
        }
        else{
            try{
                PlayList songs=userPlaylistService.DeleteSong(userId, playlistId, songsId);
                System.out.println("Playlist ID - "+songs.getId().trim());
                System.out.println("Playlist Name - "+songs.getName().trim());
                System.out.println("Song IDs - "+songs.getSongId().toString().replace(",", "").replace("[", "").replace("]", "").trim());
            }catch(RuntimeException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }



    
}
