package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IUserSongService;

public class PlayPlaylistCommand implements ICommand {
    private final IUserSongService userSongService;

    public PlayPlaylistCommand(IUserSongService userSongService)
    {
        this.userSongService=userSongService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String playlistId=tokens.get(2);
        try{
            Song currsong=userSongService.PlayPlaylist(userId, playlistId);
            System.out.println("Current Song Playing");
            System.out.println("Song - "+currsong.getName().trim());
            System.out.println("Album - "+currsong.getAlbum().trim());
            System.out.println("Artists - "+currsong.getArtist().toString().replace("[","").replace("]","").replace(", ",",").trim());
        }catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
