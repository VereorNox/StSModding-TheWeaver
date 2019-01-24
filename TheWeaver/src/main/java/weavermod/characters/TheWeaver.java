package weavermod.characters;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import weavermod.WeaverMod;
import weavermod.cards.*;
import weavermod.patches.AbstractCardEnum;
import weavermod.relics.DefaultClickableRelic;
import weavermod.relics.PlaceholderRelic;
import weavermod.relics.PlaceholderRelic2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import weavermod.relics.QueenAdministrator;

import java.util.ArrayList;
//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in WeaverMod-Character-Strings.json in the resources

public class TheWeaver extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(WeaverMod.class.getName());

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 50;
    public static final int MAX_HP = 50;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 7;
    public static final int ORB_SLOTS = 3;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = "theWeaver:DefaultCharacter";
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "weaverModResources/images/char/defaultCharacter/orb/layer1.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer2.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer3.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer4.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer5.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer6.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer1d.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer2d.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer3d.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer4d.png",
            "weaverModResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============


    // =============== CHARACTER CLASS START =================

    public TheWeaver(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "weaverModResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "weaverModResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout
                weavermod.WeaverMod.makePath(weavermod.WeaverMod.THE_WEAVER_SHOULDER_1), // campfire pose
                weavermod.WeaverMod.makePath(weavermod.WeaverMod.THE_WEAVER_SHOULDER_2), // another campfire pose
                weavermod.WeaverMod.makePath(weavermod.WeaverMod.THE_WEAVER_CORPSE), // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  

        loadAnimation(
                weavermod.WeaverMod.makePath(weavermod.WeaverMod.THE_WEAVER_SKELETON_ATLAS),
                weavermod.WeaverMod.makePath(weavermod.WeaverMod.THE_WEAVER_SKELETON_JSON),
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(PepperSpray.ID);
        retVal.add(Restrain.ID);
        retVal.add(Brace.ID);

        //retVal.add(DefaultCommonAttack.ID);
        //retVal.add(DefaultUncommonAttack.ID);
        //retVal.add(DefaultRareAttack.ID);

        //retVal.add(DefaultCommonSkill.ID);
        //retVal.add(DefaultUncommonSkill.ID);
        //retVal.add(DefaultRareSkill.ID);

        //retVal.add(DefaultCommonPower.ID);
        //retVal.add(DefaultUncommonPower.ID);
        //retVal.add(DefaultRarePower.ID);

        //retVal.add(DefaultAttackWithVariable.ID);
        //retVal.add(DefaultSecondMagicNumberSkill.ID);
        //retVal.add(OrbSkill.ID);
        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(QueenAdministrator.ID);

        UnlockTracker.markRelicAsSeen(QueenAdministrator.ID);

        return retVal;
    }

    // Character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // Character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.DEFAULT_GRAY;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return weavermod.WeaverMod.DEFAULT_GRAY;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return "The Default";
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "the Default";
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new TheWeaver(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return weavermod.WeaverMod.DEFAULT_GRAY;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() { return weavermod.WeaverMod.DEFAULT_GRAY; }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
