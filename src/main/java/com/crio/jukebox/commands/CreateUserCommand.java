package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserPlaylistService;

public class CreateUserCommand implements ICommand {
    private final IUserPlaylistService userPlaylistService;

    public CreateUserCommand(IUserPlaylistService userPlaylistService)
    {
        this.userPlaylistService=userPlaylistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
     String name=tokens.get(1);
     User user=userPlaylistService.create(name);
     System.out.println(user.getId()+" "+user.getName().trim());
        
    }
    
}
