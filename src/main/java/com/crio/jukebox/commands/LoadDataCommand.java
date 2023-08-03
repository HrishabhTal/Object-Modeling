package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserPlaylistService;

public class LoadDataCommand implements ICommand {
private final IUserPlaylistService userPlaylistService;

public LoadDataCommand(IUserPlaylistService userPlaylistService)
{
    this.userPlaylistService=userPlaylistService;
}

@Override
public void execute(List<String> tokens) {
    // TODO Auto-generated method stub
    String file=tokens.get(1);
    try{
        String data=userPlaylistService.Loadsongs(file);
        System.out.println(data);
    }catch(RuntimeException e)
    {
        System.out.println(e.getMessage());

    }
    
}
 
    
}
