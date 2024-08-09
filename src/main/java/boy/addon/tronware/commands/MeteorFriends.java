package boy.addon.tronware.commands;

import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.client.ChatHelper;
import dev.boze.api.client.FriendManager;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtCompound;

import java.io.File;
import java.io.IOException;

public class MeteorFriends extends ToggleableModule {
    @Override
    public void onEnable() {
        ChatHelper.sendMsg("BoyAddon", "Porting Meteor Friends To Boze! :3");

        File meteorFriendsFile = new File("meteor-client/friends.nbt");
        if (!meteorFriendsFile.exists()) {
            ChatHelper.sendMsg("BoyAddon", "Meteor friends file not found!");
            return;
        }

        try {
            NbtCompound nbt = NbtIo.read(meteorFriendsFile.toPath());
            if (nbt != null) {
                NbtList friendsList = nbt.getList("friends", 10);
                for (int i = 0; i < friendsList.size(); i++) {
                    NbtCompound friend = friendsList.getCompound(i);
                    String name = friend.getString("name");
                    if (FriendManager.addFriend(name)) {
                        ChatHelper.sendMsg("BoyAddon", "Added friend: " + name);
                    } else {
                        ChatHelper.sendMsg("BoyAddon", "Friend already added: " + name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            ChatHelper.sendMsg("BoyAddon", "Failed to read Meteor friends file!");
        }
    }

    public MeteorFriends() {
        super("MeteorFriends", "adds all of ur friends from meteor to booze");
    }
}