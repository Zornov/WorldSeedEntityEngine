package demo_models.weapon;

import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.worldseed.multipart.animations.AnimationHandler;
import net.worldseed.multipart.animations.AnimationHandlerImpl;
import org.jetbrains.annotations.NotNull;

public class WeaponMob extends EntityCreature {
    private final Weapon model;
    private final Player player;
    public final AnimationHandler animationHandler;

    public WeaponMob(Player player) {
        super(EntityType.SILVERFISH);
        this.setInvisible(true);

        this.model = new Weapon();
        var pos = player.getPosition();
        var gameInstance = player.getInstance();
        model.init(gameInstance, pos);

        this.player = player;
        this.setInstance(gameInstance, pos).join();
        model.addPartsAsPassengers(player);
        this.animationHandler = new AnimationHandlerImpl(this.model);
    }


    @Override
    public void update(long time) {
        super.update(time);
        var pos = player.getPosition();
        this.model.setGlobalRotation(pos.yaw());
    }

    @Override
    public void updateNewViewer(@NotNull Player player) {
        super.updateNewViewer(player);
        this.model.addViewer(player);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void updateOldViewer(@NotNull Player player) {
        super.updateOldViewer(player);
        this.model.removeViewer(player);
    }

    @Override
    public void remove() {
        super.remove();
        this.model.destroy();
    }
}
