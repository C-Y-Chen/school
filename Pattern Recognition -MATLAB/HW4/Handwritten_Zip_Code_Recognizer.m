clear all;
close all;
kk = 160;     % kk 可以設定5 10 15 20 256

% %  根據kk，產生zip_test的 Transformation Matrix:T2
% b = 'zip_test.txt';       % 讀zip_test.txt
% N = csvread(b);                         
% N(:,1) = [];                            
% mu = mean(N,2);                         % 計算mean
% MM = [];
% for i=1:256                             % 計算covariance matrix 
%    temp = N(:,i) - mu;
%     MM = [MM temp];
% end
% sigma = MM'*MM;
% [A,D] = eig(sigma);                     % 找eigenvectors和eigenvalue並排序
% vals = diag(D);
% [~,ind] = sort(abs(vals), 'descend');
% Asort = A(:,ind);
% for k = kk                              % 產生Transformation Matrix
%     T2 = Asort(:,1:k);
% end


a = 'zip_train.txt';      % 讀zip_train.txt
N = csvread(a);
answer = N(:,1);                        % 取得每筆data的答案
N(:,1) = [];
mu = mean(N,2);                         % 計算mean
MM = [];
for i=1:256                             % 計算covariance matrix
   temp = N(:,i) - mu;
    MM = [MM temp];
end
sigma = MM'*MM;
[A,D] = eig(sigma);                     % 找eigenvectors和eigenvalue並排序
vals = diag(D);
[~,ind] = sort(abs(vals), 'descend');
Asort = A(:,ind);

% 根據kk，產生zip_train的 Transformation Matrix:T
% 以T乘上zip_train產生降維後的newdata(7291 x kk)
% 根據答案0~9分為a0~a9
for k = kk
    T = Asort(:,1:k);
    newdata = N*T;
    a0 = [];a1 = [];a2 = [];a3 = [];a4 = [];a5 = [];a6 = [];a7 = [];a8 = [];a9 = [];
    s0 = [];s1 = [];s2 = [];s3 = [];s4 = [];s5 = [];s6 = [];s7 = [];s8 = [];s9 = [];
    for i=1:7291
        if answer(i) == 0
            a0 = [a0;newdata(i,:)];
        end
        if answer(i) == 1
            a1 = [a1;newdata(i,:)];
        end
        if answer(i) == 2
            a2 = [a2;newdata(i,:)];
        end
        if answer(i) == 3
            a3 = [a3;newdata(i,:)];
        end
        if answer(i) == 4
            a4 = [a4;newdata(i,:)];
        end
        if answer(i) == 5
            a5 = [a5;newdata(i,:)];
        end
        if answer(i) == 6
            a6 = [a6;newdata(i,:)];
        end
        if answer(i) == 7
            a7 = [a7;newdata(i,:)];
        end
        if answer(i) == 8
            a8 = [a8;newdata(i,:)];
        end
        if answer(i) == 9
            a9 = [a9;newdata(i,:)];
        end
    end
    
    % 計算各答案(0~9)的mean
    mm = [mean(a0)/size(a0,1);mean(a1)/size(a1,1);mean(a2)/size(a2,1);
        mean(a3)/size(a3,1);mean(a4)/size(a4,1);mean(a5)/size(a5,1);
        mean(a6)/size(a6,1);mean(a7)/size(a7,1);mean(a8)/size(a8,1);mean(a9)/size(a9,1);];
    
    % 計算各答案(0~9)的covariance matrix
    for i=1:k
        temp = a0(:,i) - mm(1,i);
        s0 = [s0 temp];
        temp = a1(:,i) - mm(2,i);
        s1 = [s1 temp];
        temp = a2(:,i) - mm(3,i);
        s2 = [s2 temp];
        temp = a3(:,i) - mm(4,i);
        s3 = [s3 temp];
        temp = a4(:,i) - mm(5,i);
        s4 = [s4 temp];
        temp = a5(:,i) - mm(6,i);
        s5 = [s5 temp];
        temp = a6(:,i) - mm(7,i);
        s6 = [s6 temp];
        temp = a7(:,i) - mm(8,i);
        s7 = [s7 temp];
        temp = a8(:,i) - mm(9,i);
        s8 = [s8 temp];
        temp = a9(:,i) - mm(10,i);
        s9 = [s9 temp];
    end
    ss = (s0'*s0)/size(a0,1);
    ss(:,:,2) = (s1'*s1)/size(a1,1);
    ss(:,:,3) = (s2'*s2)/size(a2,1);
    ss(:,:,4) = (s3'*s3)/size(a3,1);
    ss(:,:,5) = (s4'*s4)/size(a4,1);
    ss(:,:,6) = (s5'*s5)/size(a5,1);
    ss(:,:,7) = (s6'*s6)/size(a6,1);
    ss(:,:,8) = (s7'*s7)/size(a7,1);
    ss(:,:,9) = (s8'*s8)/size(a8,1);
    ss(:,:,10) = (s9'*s9)/size(a9,1);
    
    b = 'zip_test.txt';       % 讀 zip_test.txt
    M = csvread(b);
%     imshow(reshape(M(z,2:256),[16,16]));
    % rate為zip_test中0~9分布的機率
    rate = [0.18 0.13 0.1 0.08 0.10 0.08 0.08 0.07 0.08 0.09]';
    
    % 將zip_test每一筆data降維後(2007 x kk)
    % 判斷該筆資料為答案0~9的機率為 p (10 x 1) 
    % 將p乘上zip_test中rate，並找乘完後的最大值
    % 如果最大值代表得數字(0~9)等於答案則count+1
    % 最後計算辨識率
    
    count = 0;
    for i=1:2007
        p = [];
        for j=1:10
            
            p = [p;mvnpdf((T'*M(i,2:257)')',mm(j,:),ss(:,:,j)+0.0001* eye(kk))];
            
        end
        pp = [];
        for j=1:10
            pp(j,1) = p(j,1) * rate(j,1);
        end
        [v, index] = max(pp);
        if index-1 == M(i,1);
            count = count + 1;
        end
    end
    count/2007
 end
