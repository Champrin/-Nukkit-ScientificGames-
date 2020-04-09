package xyz.champrin.scientificgames.libs;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.potion.Effect;
import xyz.champrin.scientificgames.ScientificGames;
import xyz.champrin.scientificgames.mod.time.onYearEvent;
import xyz.champrin.scientificgames.untils.Burden;
import xyz.champrin.scientificgames.untils.SGPlayerSchedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SGPlayer implements Listener {

    public ScientificGames plugin = ScientificGames.getInstance();

    private String name;//名字
    private Player player;

    private double water;//水量
    private double waterBuffer;//水量缓冲
    private double waterBufferLimit = plugin.waterBufferLimit;


    private double energy;//能量
    private double energyBuffer;//能量缓冲
    private double energyBufferLimit = plugin.energyBufferLimit;


    private double food;//食物度

    private double temperature;//体温

    private double glu;//血糖

    private double pH;//体内pH值

    private double age;//年龄

    private double fatigue;
    private double fatigueBuffer;//能量缓冲
    private double fatigueBufferLimit = plugin.fatigueBufferLimit;

    private double burden;
    private double burdenMax;

    private double pStrength;//体力

    public ArrayList<String> illness = new ArrayList<>();

    public SGPlayer(String name) {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
        this.name = name;

        this.water = plugin.PlayerInC.getDouble(name + ".water");
        this.waterBuffer = plugin.PlayerInC.getDouble(name + ".waterBuffer");
        this.energy = plugin.PlayerInC.getDouble(name + ".energy");
        this.energyBuffer = plugin.PlayerInC.getDouble(name + ".energyBuffer");
        this.food = plugin.PlayerInC.getDouble(name + ".food");
        this.temperature = plugin.PlayerInC.getDouble(name + ".temperature");
        this.glu = plugin.PlayerInC.getDouble(name + ".glu");
        this.pH = plugin.PlayerInC.getDouble(name + ".pH");
        this.age = plugin.PlayerInC.getDouble(name + ".age");
        this.fatigue = plugin.PlayerInC.getDouble(name + ".fatigue");
        this.burden = plugin.PlayerInC.getDouble(name + ".burden");
        this.burdenMax = Math.round(plugin.BurdenMax * Math.pow(1.1D, age));


        this.player = plugin.getServer().getPlayer(name);


    }

    public void RunSchedule() {
        this.plugin.getServer().getScheduler().scheduleRepeatingTask(new SGPlayerSchedule(this), 20);
    }

    @Override
    public String toString() {
        return "SGPlayer{" +
                "plugin=" + plugin +
                ", name='" + name + '\'' +
                ", player=" + player +
                ", water=" + water +
                ", waterBuffer=" + waterBuffer +
                ", waterBufferLimit=" + waterBufferLimit +
                ", energy=" + energy +
                ", energyBuffer=" + energyBuffer +
                ", energyBufferLimit=" + energyBufferLimit +
                ", food=" + food +
                ", temperature=" + temperature +
                ", glu=" + glu +
                ", pH=" + pH +
                ", age=" + age +
                ", fatigue=" + fatigue +
                ", fatigueBuffer=" + fatigueBuffer +
                ", fatigueBufferLimit=" + fatigueBufferLimit +
                ", burden=" + burden +
                ", pStrength=" + pStrength +
                ", illness=" + illness +
                '}';
    }

    public void onActions() {
        toEnergyBuffer();
        toFatigueBuffer();
        toWaterBuffer();
    }

    public void toEnergyBuffer() {
        double a = energyBuffer;
        this.energyBuffer = a + 1;
        if (a >= energyBufferLimit) {
            this.energy = (energy - 1) * 0.976;
            this.energyBuffer = 0;
        }
    }

    public void toFatigueBuffer() {
        double a = fatigueBuffer;
        this.fatigueBuffer = a + 2;
        if (a >= fatigueBufferLimit) {
            this.fatigue = (fatigue + 1) * 0.926;
            this.fatigueBuffer = 0;
        }
    }

    public void toWaterBuffer() {
        double a = waterBuffer;
        this.waterBuffer = a + 1;
        if (a >= waterBufferLimit) {
            this.water = (water - 1) * 0.988;
            this.waterBuffer = 0;
        }
    }

    public void toEnergyBuffer(int multiply) {
        double a = energyBuffer;
        this.energyBuffer = (a + 1) * multiply;
        if (a >= energyBufferLimit) {
            this.energy = (energy - 1) * 0.976;
            this.energyBuffer = 0;
        }
    }

    public void toFatigueBuffer(int multiply) {
        double a = fatigueBuffer;
        this.fatigueBuffer = (a + 2) * multiply;
        if (a >= fatigueBufferLimit) {
            this.fatigue = (fatigue + 1) * 0.926;
            this.fatigueBuffer = 0;
        }
    }

    public void toWaterBuffer(int multiply) {
        double a = waterBuffer;
        this.waterBuffer = (a + 1) * multiply;
        if (a >= waterBufferLimit) {
            this.water = (water - 1) * 0.988;
            this.waterBuffer = 0;
        }
    }

    public boolean toActEnergy() {
        if (energy >= 5) {
            return true;
        }
        return false;
    }

    public boolean toActFatigue() {
        if (fatigue >= 95) {
            return true;
        }
        return false;
    }

    //TODO 体温算法

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void addWater(double water) {
        this.water = this.water + water;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void addEnergy(double energy) {
        this.energy = this.energy + energy;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public void addFood(double food) {
        this.food = this.food + food;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void addTemperature(double temperature) {
        this.temperature = this.temperature + temperature;
    }

    public double getGlu() {
        return glu;
    }

    public void setGlu(double glu) {
        this.glu = glu;
    }

    public void addGlu(double glu) {
        this.glu = this.glu + glu;
    }

    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    public void addpH(double pH) {
        this.pH = this.pH + pH;
    }

    public double getAge() {
        return age;
    }

    public double getFatigue() {
        return fatigue;
    }

    public void setFatigue(double fatigue) {
        this.fatigue = fatigue;
    }

    public void addFatigue(double fatigue) {
        this.fatigue = this.fatigue + fatigue;
    }

    public double getBurden() {
        return burden;
    }

    public void setBurden(double burden) {
        if (burden == -1.0D) {
            this.burden = new Burden().getBurden(player.getInventory());
        } else {
            this.burden = burden;
        }
    }

    public void addBurden(double burden) {
        this.burden = this.burden + burden;
    }

    public double getBurdenMax() {
        return burdenMax;
    }

    public void addEffect(int effectId, int amplifier, int duration, boolean visible) {
        player.addEffect(Effect.getEffect(effectId).setDuration(duration).setAmplifier(amplifier).setVisible(visible));
    }

    public void recoverPlayer() {
        this.fatigue = fatigue - 0.1;
        this.pStrength = pStrength - 0.1;
    }

    public void killPlayer() {
        for (int i = 0; i <= player.getMaxHealth(); i++) {
            if (player.getHealth() <= 0) break;
            this.player.setHealth(this.player.getHealth() - 0.5F);
        }
    }

    public float getSpeed() {
        if (burden > 5.0D * burdenMax) {
            return 0.1F / 10.0F;
        } else if (burden > 4.5D * burdenMax) {
            return 0.1F / 9.0F;
        } else if (burden > 4.0D * burdenMax) {
            return 0.1F / 8.0F;
        } else if (burden > 3.5D * burdenMax) {
            return 0.1F / 7.0F;
        } else if (burden > 3.0D * burdenMax) {
            return 0.1F / 6.0F;
        } else if (burden > 2.5D * burdenMax) {
            return 0.1F / 5.0F;
        } else if (burden > 2.0D * burdenMax) {
            return 0.1F / 4.0F;
        } else if (burden > 1.5D * burdenMax) {
            return 0.1F / 3.0F;
        } else {
            return burden > burdenMax ? 0.1F / 2.0F : 0.1F;
        }
    }

    public void respawn() {
        this.water = plugin.WaterMax;
        this.waterBuffer = 0.00;
        this.energy = plugin.EnergyMax;
        this.energyBuffer = 0.00;
        this.food = plugin.FoodMax;
        this.temperature = plugin.TemperatureMax;
        this.glu = plugin.GluMax;
        this.pH = plugin.pHMax;
        this.age = plugin.AgeMax;
        this.fatigue = plugin.FatigueMax;
        this.fatigueBuffer = 0.00;
        this.burden = plugin.BurdenMax;
    }

    public void saveConfig() {
        LinkedHashMap<String, Object> Map = new LinkedHashMap<>();
        Map.put("water", water);
        Map.put("waterBuffer", waterBuffer);
        Map.put("energy", energy);
        Map.put("energyBuffer", energyBuffer);
        Map.put("food", food);
        Map.put("temperature", temperature);
        Map.put("glu", glu);
        Map.put("pH", pH);
        Map.put("age", age);
        Map.put("fatigue", fatigue);
        Map.put("burden", burden);
        this.plugin.PlayerInC.set(name, Map);
        this.plugin.PlayerInC.save();
    }

    @EventHandler
    public void onYear(onYearEvent event) {
        this.age = age + 1;
        this.burdenMax = Math.round(burdenMax * 1.1D);
    }
}
