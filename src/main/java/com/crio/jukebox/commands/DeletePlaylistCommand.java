package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserPlaylistService;

public class DeletePlaylistCommand implements ICommand {
    private final IUserPlaylistService userPlaylistService;

    public DeletePlaylistCommand(IUserPlaylistService userPlaylistService)
    {
        this.userPlaylistService=userPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String playlistId=tokens.get(2);
        try{
            String op=userPlaylistService.DeletePlaylist(userId,playlistId);
            System.out.println(op);
        }catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
