//
//  AppDelegate+apsalar.m
//
//

#import "AppDelegate+apsalar.h"

@implementation AppDelegate (apsalar)

- (void)applicationDidBecomeActive:(UIApplication *)application {

  NSString *apiKey = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"ApsalarKey"];
  NSString *apiSecret = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"ApsalarSecret"];
  [Apsalar startSession:apiKey withKey:apiSecret];

}

@end
