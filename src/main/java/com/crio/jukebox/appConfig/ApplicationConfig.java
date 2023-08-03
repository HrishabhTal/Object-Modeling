package com.crio.jukebox.appConfig;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.commands.Commandinvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IUserPlaylistService;
import com.crio.jukebox.services.IUserSongService;
import com.crio.jukebox.services.UserPlaylistService;
import com.crio.jukebox.services.UserSongService;

public class ApplicationConfig {
    private final IUserRepository userRepository=new UserRepository();
    private final ISongRepository songRepository=new SongRepository();
    private final IPlayListRepository playlistrepository=new PlayListRepository();

    private final IUserPlaylistService userplaylistService=new UserPlaylistService(userRepository,songRepository,playlistrepository);
    private final IUserSongService usersongService=new UserSongService(userRepository,songRepository,playlistrepository);

    private final LoadDataCommand loadDataCommand=new LoadDataCommand(userplaylistService);
    private final CreateUserCommand createUserCommand=new CreateUserCommand(userplaylistService);
    private final CreatePlaylistCommand createPlaylistCommand=new CreatePlaylistCommand(userplaylistService);
    private final DeletePlaylistCommand deletePlaylistCommand=new DeletePlaylistCommand(userplaylistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand=new ModifyPlaylistCommand(userplaylistService);
    private final PlayPlaylistCommand playPlaylistCommand=new PlayPlaylistCommand(usersongService);
    private final PlaySongCommand playSongCommand=new PlaySongCommand(usersongService);

    private final Commandinvoker commandinvoker=new Commandinvoker();

    public Commandinvoker getCommandinvoker(){
        commandinvoker.register("LOAD-DATA",loadDataCommand);
        commandinvoker.register("CREATE-USER",createUserCommand);
        commandinvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandinvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandinvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandinvoker.register("PLAY-PLAYLIST",playPlaylistCommand);
        commandinvoker.register("PLAY-SONG",playSongCommand);
        return commandinvoker;
    }
    
}
