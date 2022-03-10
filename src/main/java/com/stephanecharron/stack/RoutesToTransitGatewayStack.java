package com.stephanecharron.stack;

import com.stephanecharron.stackProps.VpcListStackProps;
import software.amazon.awscdk.Fn;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.*;
import software.constructs.Construct;


public class RoutesToTransitGatewayStack extends Stack {
    public RoutesToTransitGatewayStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    @lombok.Builder
    public RoutesToTransitGatewayStack(final Construct scope, final String id, final VpcListStackProps vpcListStackProps) {
        super(scope, id, vpcListStackProps);

        Vpc vpc1 = vpcListStackProps.getVpcs().get(0);
        Vpc vpc2 = vpcListStackProps.getVpcs().get(1);

        int index = 0;
        for (ISubnet subnet: vpc1.getPrivateSubnets()){
            CfnRoute.Builder.create(this, "RouteFromPrivateSubnetOfVpc1ToVpc2_"+index)
                    .routeTableId(subnet.getRouteTable().getRouteTableId())
                    .destinationCidrBlock(vpc2.getVpcCidrBlock())
                    .transitGatewayId(Fn.importValue("TransitGatewayId"))
                    .build();

            index++;
        }

        index = 0;
        for (ISubnet subnet: vpc2.getPrivateSubnets()){
            CfnRoute.Builder.create(this, "RouteFromPrivateSubnetOfVpc2ToVpc1_"+index)
                    .routeTableId(subnet.getRouteTable().getRouteTableId())
                    .destinationCidrBlock(vpc1.getVpcCidrBlock())
                    .transitGatewayId(Fn.importValue("TransitGatewayId"))
                    .build();
            index++;
        }
    }
}
