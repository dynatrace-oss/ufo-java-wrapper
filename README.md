# Dynatrace UFO - java wrapper

### About
You can use this java wrapper to simplify controlling your Dynatrace UFO 

    UfoCommand.newBuilder()
            .withUfoIp(<myIp>)
            .lanesScenario(PredefinedScenarios.PIPELINE_FAILED)
            .logoReset()
            .build()
            .execute()
            
instead of putting the rest command together on your own.
                        
    http://<myIp>/api?top_init&top_init&top=0|15|ff0000&logo_reset&bottom_init&bottom=0|15|ff0000

The scenarios can become quite complicated, for instance if you want to display the flag of germany:

    http://<myIp>/api?top_init&top=0|2|000000&top=2|2|ff0000&top=4|2|ffff00&top=6|9|ffffff&top_whirl=200&bottom_init&bottom=0|2|000000&bottom=2|2|ff0000&bottom=4|2|ffff00&bottom=6|9|ffffff&bottom_whirl=200
    
or if you might want to show random colors on both lanes

    http://<myIp>/api?top=0|1|78adc6&top=1|1|14ca50&top=2|1|e300ca&top=3|1|047a22&top=4|1|c8d70e&top=5|1|3308b8&top=6|1|806c75&top=7|1|991922&top=8|1|c1e378&top=9|1|363a15&top=10|1|a9f5dd&top=11|1|a71e88&top=12|1|227c65&top=13|1|86cd1a&top=14|1|84025c&top_whirl=200&bottom=0|1|04aeda&bottom=1|1|7947b9&bottom=2|1|60d7fb&bottom=3|1|e67827&bottom=4|1|8562ba&bottom=5|1|3ebcff&bottom=6|1|ba0cd8&bottom=7|1|50f1ca&bottom=8|1|526be0&bottom=9|1|6ff038&bottom=10|1|ca136f&bottom=11|1|3a6541&bottom=12|1|cab4cd&bottom=13|1|b8c1b8&bottom=14|1|5aef3f&bottom_whirl=200|ccw

### Dynatrace UFO - How it works
If you are interested in deeper knowledge how the Dynatrace UFO is operating, or if you want to get deeper knowledge in the commands available on the UFO's rest api - don't miss to visit the UFO repository!

https://github.com/Dynatrace/ufo-esp32
    
### Commands
You can simply control your ufo with UfoCommandBuilder

    UfoCommand command = UfoCommand.newBuilder()
                                    ...
                                    .build();

| Command      | description           |
| ------------- |-------------|
| build()  | Builds the command |
| withUfoIp(String)      | sets the ip address of the ufo - defaults to 192.168.4.1  |
| topReset()      | resets the top lane - all black, no whirl, no morph, no background  |
| bottomReset()      | resets the bottom lane - all black, no whirl, no morph, no background  |
| logoReset()      | resets the logo to the Dynatrace default logo |
| lanesReset()      | resets both lanes |
| reset()      | resets both lanes and the logo at once  |
| topColor(offset, length, LedColor[] or String[]) | Sets the color of specified LEDs of the top lane |
| bottomColor(offset, length, LedColor[] or String[]) | Sets the color of specified LEDs of the bottom lane |
| topColors(columnWidth, LedColor[] or String[]) | Sets the colors of the top lane |
| bottomColors(columnWidth, LedColor[] or String[]) | Sets the colors of the bottom lane |
| lanesColors(columnWidth, LedColor[] or String[])  | Sets the colors of both lanes |
| topWhirl(WhirlSpeed[, WhirlDirection])  | sets whirling of the top lane  |
| bottomWhirl(WhirlSpeed[, WhirlDirection])  | sets whirling of the bottom lane  |
| lanesWhirl(WhirlSpeed[, WhirlDirection])  | sets whirling of both lanes  |
| lanesWhirlOpposite(WhirlSpeed)  | sets whirling of both lanes, top lane clock wise, bottom lane counter clock wise  |
| topMorph(MorphSpeed)  | sets morphing of the top lane  |
| bottomMorph(MorphSpeed)  | sets morphing of the bottom lane  |
| lanesMorph(MorphSpeed)  | sets morphing of both lanes  |
| topBackGround(String or LedColor)  | sets the background color of the top lane  |
| bottomBackGround(String or LedColor)  | sets the background color of the bottom lane  |
| lanesBackGround(String or LedColor)  | sets the background color of both lanes  |
| topFill(String or LedColor)  | fills the top lane with the specified color  |
| bottomFill(String or LedColor)  | fills the bottom lane with the specified color  |
| topRandom()  | fills the top lane with random colors  |
| bottomRandom()  | fills the bottom lane with random colors  |
| random()  | fills both lanes randomly and sets the whirl of the lanes oppositely |
| topScenario()  | Sets a scenario (whirl, morph, colors, background) on the top lane |
| bottomScenario()  | Sets a scenario (whirl, morph, colors, background) on the bottom lane |
| lanesScenario()  | Sets a scenario (whirl, morph, colors, background) on both lanes |
| logoScenario()  | Sets a scenario (only first four colors) for the logo |

### UfoScenario
You can create ufo scenarios via UfoScenarioBuilder class

    UfoScenario scenario = UfoScenario.newBuilder()
                                    ...
                                    .build();
                                    
| Command      | description           |
| ------------- |-------------|
| build()  | Builds the scenario |
| columns(LedColor[] or String[])      | color pattern of the scenario  |
| columnWidth(int)      | width of every column in the pattern  |
| backgroundColor(LedColor or String)      | background color |
| whirl(WhirlSpeed[, WhirlDirection])  | sets whirling |
| morph(WhirlSpeed)  | sets morphing |
| fill(boolean)  | sets fill - colors do get repeated |

### Predefined Scenarios
There are a couple of predefined scenarios defined. You can access them via *PredefinedScenarios* class

    UfoCommand.newBuilder()
            .topScenario(PredefinedScenarios.PIPELINE_COMPILING)
            .build()
            .execute()

| scenario        | description           |
| ------------- |-------------|
| ALL_WHITE      | all lights white      |
| ALL_BLACK | all lights disabled (no light) |
| PIPELINE_FAILED | lane colored red |
| PIPELINE_SUCCEEDED | lane colored green |
| PIPELINE_COMPILING | lane colored blue - morphing| 

### Scenario sets - define your own (YAML)
You can define and load your own scenarios via yaml file 
    
    PredefinedScenarios.loadYaml(<MYCLASS>.class.getClassLoader().getResourceAsStream("<myResource>.yaml")))
    
### YAML file example

    !!com.dynatrace.ufowrapper.yaml.YamlContent
    scenarios:
      - name: "it-rotate-fast"
        colors:
          - "Green"
          - "White"
          - "Red"
        whirl: Fast
        columnWidth: 4
        morph: Fast
        background: DtDarkGray

