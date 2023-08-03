package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.services.IUserPlaylistService;

public class CreatePlaylistCommand implements ICommand {
    private final IUserPlaylistService userPlaylistService;

    public CreatePlaylistCommand(IUserPlaylistService userPlaylistService)
    {
        this.userPlaylistService=userPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String name=tokens.get(2);
        List<String> songsId=new ArrayList<String>();
        for(int i=3;i<tokens.size();i++){
            songsId.add(tokens.get(i));
        }
        try{
            String playlist=userPlaylistService.CreatePlaylist(userId, name, songsId);
            System.out.println("Playlist ID - "+playlist);
        }catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
