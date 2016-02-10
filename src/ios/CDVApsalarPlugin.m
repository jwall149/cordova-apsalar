#import "CDVApsalarPlugin.h"

@implementation CDVApsalarPlugin

- (void)sendEvent:(CDVInvokedUrlCommand*) command
{
    NSString *eventName = [command.arguments objectAtIndex:0];
    NSString *key = [command.arguments objectAtIndex:1];
    NSString *value = [command.arguments objectAtIndex:2];

    NSDictionary *params = @{ key: value };
    [Apsalar event:eventName withArgs:params];
}

@end
