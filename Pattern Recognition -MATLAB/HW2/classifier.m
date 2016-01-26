function classifier()
    format longG
    for c=0:18
        input = sprintf('input/%d.jpg',c);
            img=imread( input );
            R=img(:,:,1);
            G=img(:,:,2);
            B=img(:,:,3);
            [x, y] = size(R);
            count = 0;
            mu1 = getRGBMu(0);  % 0 means non-skin-color set
            mu2 = getRGBMu(1);  % 1 means skin-color set
            for i=1:x 
                for j=1:y
                    X = [R(i,j) G(i,j) B(i,j)];
                    X = double(1.4*X);
                    sig1 = getSigma(X,mu1);
                    sig2 = getSigma(X,mu2);
                    check = gaussianMixtureModel(X,mu1,mu2,sig1,sig2);
                    if  check == 0
                        img(i,j,1) = 255;
                        img(i,j,2) = 255;
                        img(i,j,3) = 255;
                    end
                    count = count +1;
                    disp(count);
                    disp(check);
                end
            end
        output = sprintf('output/test1/%d.jpg',c);
        imwrite(img,output);
    end
end

function class = gaussianMixtureModel(x,mu1,mu2,sig1,sig2)
    p1 = mvnpdf(x,mu1,sig1); 
    p1 = p1 * 1/2;
    p2 = mvnpdf(x,mu2,sig2);
    p2 = p2 * 1/2;
    if p2>p1    % p1 = non-skin-color set ; p2 = skin-color set
        class = 1;
    else
        class = 0;
    end

end
function mu = getRGBMu( type )
    
    rmu = 0;
    gmu = 0;
    bmu = 0;
    if type == 0
        for i=1:18
            path = sprintf('training set1/%d.jpg',i);
            img=imread( path );
            R=img(:,:,1);
            G=img(:,:,2);
            B=img(:,:,3);
           [pr,pg,pb] = getPixelMu(R,G,B);
            rmu = rmu + pr;
            gmu = gmu + pg;
            bmu = bmu + pb;
        end
        mu = [rmu/18 gmu/18 bmu/18];
    else
        for i=1:18
            path = sprintf('training set2/%d.jpg',i);
            img=imread( path );
            R=img(:,:,1);
            G=img(:,:,2);
            B=img(:,:,3);
            [pr,pg,pb] = getPixelMu(R,G,B);
            rmu = rmu + pr;
            gmu = gmu + pg;
            bmu = bmu + pb;
        end
        mu = [rmu/18 gmu/18 bmu/18];
    end
    
end
function [pr,pg,pb] = getPixelMu( pixelR,pixelG,pixelB )
    [x,y] = size(pixelR);
    r(:,:) = 0;
    g(:,:) = 0;
    b(:,:) = 0;
    count = 0;
        for i=1:x
            for j=1:y
                if  (pixelR(i,j) ~= 255) && (pixelG(i,j) ~= 255) && (pixelB(i,j) ~= 255)
                    r(i,j) =  pixelR(i,j);
                    g(i,j) =  pixelG(i,j);
                    b(i,j) =  pixelB(i,j);
                    count = count + 1;
                end
            end
        end
        tempR = 0;
        tempG = 0;
        tempB = 0;
    [x2,y2] = size(r);
    for i=1:x2
        for j=1:y2
            sR = sum(r(i,j));
            tempR = tempR + sR;
            sG = sum(g(i,j));
            tempG = tempG + sG;
            sB = sum(b(i,j));
            tempB = tempB + sB;
        end
    end
    pr = tempR / count;
    pg = tempG / count;
    pb = tempB / count;
end
function sigma = getSigma(x,mu)
    s(:,:) = 0;
    for i=1:18
        sig = (x-mu)'*(x-mu);
        s = s + sig;
    end
    sigma = s/17 + .0001 * eye(3);
end