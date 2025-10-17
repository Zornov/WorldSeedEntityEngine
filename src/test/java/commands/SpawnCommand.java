package commands;

import demo_models.weapon.WeaponMob;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerHandAnimationEvent;

public class SpawnCommand extends Command {
    public SpawnCommand() {
        super("spawn");

        setDefaultExecutor((sender, _) -> {
            final Player player = (Player) sender;
            player.setInvisible(true);
            var model = new WeaponMob(player);

            MinecraftServer.getGlobalEventHandler().addListener(PlayerHandAnimationEvent.class, event -> {
                if (model.animationHandler.getPlaying() != null) return;
                model.animationHandler.playOnce("animation.gun.shoot", () -> {
                    IO.println("Animation finished");
                });
            });
        });
    }
}
