#import "CDVApsalarPlugin.h"

@implementation CDVApsalarPlugin

- (void)pluginInitialize {
    // Add notification listener for tracking app activity with Apsalar Event
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(applicationDidBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
}

- (void)applicationDidBecomeActive {
    // Start appsalar session
    NSString *apiKey = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"ApsalarKey"];
    NSString *apiSecret = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"ApsalarSecret"];
    [Apsalar startSession:apiKey withKey:apiSecret];
}

- (void)sendEvent:(CDVInvokedUrlCommand*) command
{
    NSString *eventName = [command.arguments objectAtIndex:0];
    NSObject *value = [command.arguments objectAtIndex:1];
    if (value && [value isKindOfClass:[NSDictionary class]]) {
        [Apsalar event:eventName withArgs:(NSDictionary*)value];
    } else {
        [Apsalar event:eventName];
    }
}

@end

